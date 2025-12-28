package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDto request) {
        System.out.println("Register => " + request.getEmail());
        return ResponseEntity.ok("User registered successfully: " + request.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        System.out.println("Login => " + request.getEmail());
        return ResponseEntity.ok("Login success");
    }
}
