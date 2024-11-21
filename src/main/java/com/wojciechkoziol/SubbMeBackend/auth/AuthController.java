package com.wojciechkoziol.SubbMeBackend.auth;

import com.wojciechkoziol.SubbMeBackend.auth.dto.AuthResponseDTO;
import com.wojciechkoziol.SubbMeBackend.auth.dto.LoginDTO;
import com.wojciechkoziol.SubbMeBackend.auth.dto.RegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public AuthResponseDTO register(@RequestBody RegisterDTO body) {
        return authService.registerUser(body.getEmail(), body.getPassword(), body.getFirstName(), body.getLastName());
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginDTO body) {
        return authService.loginUser(body.getEmail(), body.getPassword());
    }
}
