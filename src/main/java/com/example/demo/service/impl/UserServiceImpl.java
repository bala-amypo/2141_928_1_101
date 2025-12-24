package com.example.demo.service.impl;

import com.example.demo.dto.UserRegisterDto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User registerUser(UserRegisterDto dto) {
        Set<Role> roles = new HashSet<>();
        if (dto.getRoles() != null) {
            dto.getRoles().forEach(roleName -> {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                roles.add(role);
            });
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(roles)
                .build();

        return userRepository.save(user);
    }
}
