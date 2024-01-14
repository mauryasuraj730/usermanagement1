package com.example.UserManagement.usermanagement.user.modal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpPayload {
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private String password;

}
