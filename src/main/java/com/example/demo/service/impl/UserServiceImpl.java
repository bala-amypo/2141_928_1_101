package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtProvider;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtProvider jwt;

    public UserServiceImpl(UserRepository repo,
                           PasswordEncoder encoder,
                           JwtProvider jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    @Override
    public User register(UserRegisterDto dto) {

        Set<Role> roles = dto.getRoles() == null ?
                Set.of(Role.ROLE_USER) :
                dto.getRoles()
                        .stream()
                        .map(Role::valueOf)
                        .collect(Collectors.toSet());

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .roles(roles)
                .createdAt(LocalDateTime.now())
                .build();

        return repo.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        User user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwt.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(Enum::name)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
