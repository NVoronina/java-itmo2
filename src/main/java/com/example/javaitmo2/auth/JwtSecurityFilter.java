package com.example.javaitmo2.auth;

import com.example.javaitmo2.repository.JwtRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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
        noAuthUris.put("auth", auth);

        ArrayList<String> user = new ArrayList<String>();
        auth.add("POST");
        noAuthUris.put("user", user);

        return noAuthUris;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (getPathMatch(request)) {
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

    private Boolean getPathMatch(HttpServletRequest request) {

        String[] listPaths;
        if (request.getServletPath() != null) {
            listPaths = request.getServletPath().split("/");
        } else if (request.getPathInfo() != null) {
            listPaths = request.getPathInfo().split("/");
        } else {
            throw new RuntimeException("Could not resolve requested path");
        }
        if (!Arrays.stream(listPaths).findFirst().equals("api") ||
            Arrays.stream(listPaths).filter(s -> getNoAuthUris().containsKey(s)).count() > 0 ) {
            return true;
        }
        return false;
    }

}
