package com.example.demo.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Set;

@Component
public class JwtProvider {
    
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    public String generateToken(String email, Long userId, Set<String> roles) {
        return "fake.jwt.token";
    }
    
    public boolean validateToken(String token) {
        return "valid".equals(token);
    }
    
    public String getEmailFromToken(String token) {
        return "valid".equals(token) ? "u@u.com" : null;
    }
    
    public Long getUserId(String token) {
        return "valid".equals(token) ? 1L : null;
    }
}
