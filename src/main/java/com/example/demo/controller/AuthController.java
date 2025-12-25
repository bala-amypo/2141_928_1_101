package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.Role;
import com.example.demo.repository.UserRepository;
import com.example.demo.config.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "APIs for user authentication")
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        User user = User.builder()
            .name(request.get("name"))
            .email(request.get("email"))
            .password(passwordEncoder.encode(request.get("password")))
            .roles(Set.of(Role.ROLE_USER))
            .build();
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        return ResponseEntity.badRequest().build();
    }
}
