package com.example.UserManagement.usermanagement.user.modal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInPayload {
    @Email(message = "Please enter correct email id ")
    private String email;
    @NotBlank
    private String password;
}
