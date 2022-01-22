package com.example.javaitmo2.service;

import com.example.javaitmo2.dto.response.TokenResponse;
import com.example.javaitmo2.dto.request.UserRequest;
import com.example.javaitmo2.dto.response.UserResponse;
import com.example.javaitmo2.repository.JwtRepository;
import com.example.javaitmo2.repository.NotFoundException;
import com.example.javaitmo2.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;
    private JwtRepository jwtRepository;

    public UserService(UserRepository repository, JwtRepository jwtRepository) {
        this.repository = repository;
        this.jwtRepository = jwtRepository;
    }

    public TokenResponse authUser(UserRequest user) throws NotFoundException {
        UserResponse existingUser = this.repository.getByLoginPassword(user.getEmail(), user.getPassword());

        return new TokenResponse(this.jwtRepository.getToken(existingUser));
    }
}
