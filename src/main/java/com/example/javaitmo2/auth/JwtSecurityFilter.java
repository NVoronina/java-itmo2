package com.example.javaitmo2.auth;

import com.example.javaitmo2.repository.JwtRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtSecurityFilter extends OncePerRequestFilter {

    JwtRepository jwtRepository;

    public JwtSecurityFilter(JwtRepository jwtRepository) {
        this.jwtRepository = jwtRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getServletPath().equals("/auth/login") || request.getPathInfo().equals("/auth/login")) {
            filterChain.doFilter(request, response);
        } else {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && !authHeader.isEmpty()) {
                String jwtToken = authHeader.replace("Bearer ", "");
                if (this.jwtRepository.validateJwtToken(jwtToken)) {
                    filterChain.doFilter(request, response);
                }
            }
            response.setStatus(401);
        }
    }
}
