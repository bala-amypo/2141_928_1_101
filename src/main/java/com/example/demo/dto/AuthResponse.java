package com.example.demo.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
public class AuthResponse {
    private String token;
    private Long userId;
    private String email;
    private Set<String> roles;
}
