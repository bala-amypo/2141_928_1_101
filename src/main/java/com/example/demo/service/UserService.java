package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.User;

public interface UserService {

    User register(UserRegisterDto dto);

    AuthResponse login(AuthRequest request);

    User getByEmail(String email);
}
