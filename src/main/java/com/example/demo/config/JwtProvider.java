package com.example.demo.config;

import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class JwtProvider {
    
    public String generateToken(String email, Long userId, Set<String> roles) {
        return "jwt.token.here";
    }
    
    public boolean validateToken(String token) {
        return "valid".equals(token);
    }
    
    public String getEmailFromToken(String token) {
        return "user@example.com";
    }
    
    public Long getUserId(String token) {
        return "valid".equals(token) ? 1L : null;
    }
}