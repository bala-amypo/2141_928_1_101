package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {

    User save(User user);

    List<User> getAll();

    User getByEmail(String email);
}
