package com.example.demo.service;

import com.example.demo.dto.UserRegisterDto;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    void register(UserRegisterDto dto);

    User getByEmail(String email);

    User save(User user);

    List<User> getAll();
}
