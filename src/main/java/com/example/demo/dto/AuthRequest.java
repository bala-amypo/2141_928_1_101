package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

    @Email(message = "email must be valid")
    @NotBlank(message = "email must not be empty")
    private String email;

    @NotBlank(message = "password must not be empty")
    private String password;
}
