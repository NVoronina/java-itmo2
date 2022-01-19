package com.example.javaitmo2.service;

import com.example.javaitmo2.dto.Token;
import com.example.javaitmo2.dto.UserRequest;
import com.example.javaitmo2.dto.UserResponse;
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

    public Token authUser(UserRequest user) throws NotFoundException {
        UserResponse existingUser = this.repository.getByLoginPassword(user.getEmail(), user.getPassword());

        return new Token(this.jwtRepository.getToken(existingUser));
    }
}
