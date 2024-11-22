package com.wojciechkoziol.SubbMeBackend.auth;

import com.wojciechkoziol.SubbMeBackend.auth.dto.LoginDTO;
import com.wojciechkoziol.SubbMeBackend.auth.dto.RegisterDTO;
import com.wojciechkoziol.SubbMeBackend.auth.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterDTO body) {
        return authService.registerUser(body.getEmail(), body.getPassword(), body.getFirstName(), body.getLastName());
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody LoginDTO body) {
        return authService.loginUser(body.getEmail(), body.getPassword());
    }
}
