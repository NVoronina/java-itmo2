package com.example.javaitmo2.auth;

import com.example.javaitmo2.repository.JwtRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtSecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getServletPath().equals("/auth/login")) {
            filterChain.doFilter(request, response);
        } else {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && !authHeader.isEmpty()) {
                String jwtToken = authHeader.replace("Bearer ", "");
                if (new JwtRepository().validateJwtToken(jwtToken)) {
                    System.out.println("here ok");
                    filterChain.doFilter(request, response);
                }
            }
            response.setStatus(401);
        }
    }
}
