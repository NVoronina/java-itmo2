package com.example.javaitmo2.auth;

import com.example.javaitmo2.repository.JwtRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtSecurityFilter extends OncePerRequestFilter {

    JwtRepository jwtRepository;

    private static Map<String, List<String>> noAuthUris;

    public JwtSecurityFilter(JwtRepository jwtRepository) {
        this.jwtRepository = jwtRepository;
    }

    public static Map<String, List<String>> getNoAuthUris() {

        if (noAuthUris != null) {
            return noAuthUris;
        }
        noAuthUris = new HashMap<>();
        ArrayList<String> auth = new ArrayList<String>();
        auth.add("ALL");
        noAuthUris.put("/auth/login", auth);

        ArrayList<String> user = new ArrayList<String>();
        auth.add("POST");
        noAuthUris.put("/user", user);

        return noAuthUris;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (
            (request.getServletPath() != null && getNoAuthUris().containsKey(request.getServletPath())) ||
            (request.getPathInfo() != null && getNoAuthUris().containsKey(request.getPathInfo()))
        )
        {
            filterChain.doFilter(request, response);
        } else {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && !authHeader.isEmpty()) {
                String jwtToken = authHeader.replace("Bearer ", "");
                if (this.jwtRepository.validateJwtToken(jwtToken)) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            response.setStatus(401);
        }
    }
}
