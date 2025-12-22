package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.entity.User;

public interface UserService {

    void register(UserRegisterDto dto);

    AuthResponse login(AuthRequest request);

    User getByEmail(String email);
}
