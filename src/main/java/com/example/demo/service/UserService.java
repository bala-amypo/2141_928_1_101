package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User getByEmail(String email);
    User saveUser(User user);
}
