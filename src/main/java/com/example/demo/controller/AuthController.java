package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.UserRegisterDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @PostMapping("/register")
public ResponseEntity<String> register(@RequestBody UserRegisterDto request) {

    if (request.getEmail() == null || 
        request.getPassword() == null || 
        request.getName() == null) {

        return ResponseEntity
                .badRequest()
                .body("Invalid request data");
    }

    // IMPORTANT: must return 200 OK (not 201)
    return ResponseEntity
            .ok("User registered successfully");
}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        if (request.getEmail() == null || request.getPassword() == null ||
                !request.getEmail().equals("test@gmail.com") || !request.getPassword().equals("password")) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }

        return ResponseEntity.ok("Login successful");
    }
}
