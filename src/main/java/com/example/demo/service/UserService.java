package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    // auth
    User register(UserRegisterDto dto);
    AuthResponse login(AuthRequest request);

    // user crud (needed by UserController)
    User save(User user);
    List<User> getAll();

    // used by JWT
    User getByEmail(String email);
}
