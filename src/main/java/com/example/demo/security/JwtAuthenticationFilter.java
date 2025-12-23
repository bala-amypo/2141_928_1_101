package com.example.demo.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService service;

    public JwtAuthenticationFilter(JwtProvider jwtProvider,
                                   CustomUserDetailsService service) {
        this.jwtProvider = jwtProvider;
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws ServletException, IOException {

        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtProvider.validateToken(token)) {
                String email = jwtProvider.getEmail(token);
                UserDetails user = service.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                user, null, user.getAuthorities());

                auth.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(req));

                SecurityContextHolder.getContext()
                        .setAuthentication(auth);
            }
        }
        chain.doFilter(req, res);
    }
}
