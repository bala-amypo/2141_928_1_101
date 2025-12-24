package com.example.demo.security;


import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {


private final UserRepository repository;


public CustomUserDetailsService(UserRepository repository) {
this.repository = repository;
}


@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
var user = repository.findByEmail(email)
.orElseThrow(() -> new UsernameNotFoundException("User not found"));


return org.springframework.security.core.userdetails.User
.withUsername(user.getEmail())
.password(user.getPassword())
.authorities(user.getRoles().stream().map(Enum::name).toList())
.build();
}
}