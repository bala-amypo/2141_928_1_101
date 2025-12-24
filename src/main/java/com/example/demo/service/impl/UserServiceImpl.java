package com.example.demo.service.impl;


import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
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


private final UserRepository repository;
private final PasswordEncoder encoder;
private final JwtProvider jwtProvider;


public UserServiceImpl(UserRepository repository, PasswordEncoder encoder, JwtProvider jwtProvider) {
this.repository = repository;
this.encoder = encoder;
this.jwtProvider = jwtProvider;
}


@Override
public User register(UserRegisterDto dto) {
if (dto.getName() == null || dto.getName().isBlank() || dto.getPassword() == null || dto.getPassword().isBlank()) {
throw new IllegalArgumentException("Invalid user data");
}
repository.findByEmail(dto.getEmail()).ifPresent(u -> {
throw new IllegalArgumentException("Email already exists");
});


Set<Role> roles = dto.getRoles() == null || dto.getRoles().isEmpty()
? Set.of(Role.ROLE_USER)
: dto.getRoles().stream().map(Role::valueOf).collect(Collectors.toSet());


User user = User.builder()
.name(dto.getName())
.email(dto.getEmail())
.password(encoder.encode(dto.getPassword()))
.roles(roles)
.createdAt(LocalDateTime.now())
.build();


return repository.save(user);
}


@Override
public AuthResponse login(AuthRequest request) {
User user = repository.findByEmail(request.getEmail())
.orElseThrow(() -> new ResourceNotFoundException("User not found"));


if (!encoder.matches(request.getPassword(), user.getPassword())) {
throw new IllegalArgumentException("Invalid credentials");
}


Set<String> roles = user.getRoles().stream().map(Enum::name).collect(Collectors.toSet());
String token = jwtProvider.generateToken(user.getId(), user.getEmail(), roles);


return new AuthResponse(token, user.getId(), user.getEmail(), roles);
}


@Override
public User getByEmail(String email) {
return repository.findByEmail(email)
.orElseThrow(() -> new ResourceNotFoundException("User not found"));
}
}