package com.example.demo.security;


import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


private final JwtProvider jwtProvider;


public JwtAuthenticationFilter(JwtProvider jwtProvider) {
this.jwtProvider = jwtProvider;
}


@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
FilterChain filterChain) throws ServletException, IOException {
String header = request.getHeader("Authorization");
if (header != null && header.startsWith("Bearer ")) {
String token = header.substring(7);
try {
Claims claims = jwtProvider.getClaims(token);
String email = claims.getSubject();
List<String> roles = claims.get("roles", List.class);


var authorities = roles.stream()
.map(SimpleGrantedAuthority::new)
.collect(Collectors.toList());


var auth = new UsernamePasswordAuthenticationToken(email, null, authorities);
SecurityContextHolder.getContext().setAuthentication(auth);
} catch (Exception ignored) {}
}
filterChain.doFilter(request, response);
}
}