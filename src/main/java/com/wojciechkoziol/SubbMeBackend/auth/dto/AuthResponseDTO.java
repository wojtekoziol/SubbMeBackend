package com.wojciechkoziol.SubbMeBackend.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthResponseDTO {
    private UserDTO user;
    private String token;
}
