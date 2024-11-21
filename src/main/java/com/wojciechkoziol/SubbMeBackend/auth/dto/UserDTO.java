package com.wojciechkoziol.SubbMeBackend.auth.dto;

import com.wojciechkoziol.SubbMeBackend.auth.user.AppUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;

    public UserDTO(AppUser user) {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
}
