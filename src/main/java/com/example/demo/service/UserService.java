package com.example.demo.service;

import com.example.demo.dto.UserRegisterDto;
import com.example.demo.entity.User;

public interface UserService {

    void register(UserRegisterDto dto);

    User getByEmail(String email);
}
