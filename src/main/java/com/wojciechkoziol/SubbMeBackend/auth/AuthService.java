package com.wojciechkoziol.SubbMeBackend.auth;

import com.wojciechkoziol.SubbMeBackend.auth.dto.UserDTO;
import com.wojciechkoziol.SubbMeBackend.auth.role.Role;
import com.wojciechkoziol.SubbMeBackend.auth.role.RoleRepository;
import com.wojciechkoziol.SubbMeBackend.auth.token.TokenService;
import com.wojciechkoziol.SubbMeBackend.auth.user.AppUser;
import com.wojciechkoziol.SubbMeBackend.auth.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthService {

    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public UserDTO registerUser(String email, String password, String firstName, String lastName) {
        if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            throw new IllegalStateException("Email, password, firstName nor lastName cannot be empty");
        }

        String encodedPassword = passwordEncoder.encode(password);
        Optional<Role> roleOptional = roleRepository.findByAuthority("USER");
        if (roleOptional.isEmpty()) {
            throw new IllegalStateException("Role not found");
        }

        Set<Role> authorities = new HashSet<>();
        authorities.add(roleOptional.get());

        Optional<AppUser> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            throw new IllegalStateException("User already exists");
        }

        AppUser user = new AppUser(email, encodedPassword, firstName, lastName, authorities);
        userRepository.save(user);
        return loginUser(email, password);
    }

    public UserDTO loginUser(String email, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );

            String token = tokenService.generateToken(auth);

            Optional<AppUser> userOptional = userRepository.findByEmail(email);
            if (userOptional.isEmpty()) {
                throw new UsernameNotFoundException("User not found");
            }

            AppUser user =  userOptional.get();
            return new UserDTO(user, token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
