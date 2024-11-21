package com.wojciechkoziol.SubbMeBackend.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO extends LoginDTO {

    private String firstName;
    private String lastName;

    public RegisterDTO(String email, String password, String firstName, String lastName) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
