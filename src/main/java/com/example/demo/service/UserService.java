package com.example.demo.service;

import com.example.demo.dto.UserRegisterDto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import java.util.List;

public interface UserService {
    User register(UserRegisterDto dto);
    User login(String email, String password);
    User getByEmail(String email);
    List<Role> getAllRoles();
}
