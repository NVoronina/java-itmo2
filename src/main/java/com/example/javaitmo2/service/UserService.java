package com.example.javaitmo2.service;

import com.example.javaitmo2.conf.UserConf;
import com.example.javaitmo2.dto.response.TokenResponse;
import com.example.javaitmo2.dto.request.UserRequest;
import com.example.javaitmo2.entity.UserEntity;
import com.example.javaitmo2.repository.JwtRepository;
import com.example.javaitmo2.repository.NotFoundException;
import com.example.javaitmo2.repository.UserRepository;
import com.example.javaitmo2.repository.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;
    private JwtRepository jwtRepository;
    private ModelMapper modelMapper;

    public UserService(UserRepository repository, JwtRepository jwtRepository, ModelMapper modelMapper) {
        this.repository = repository;
        this.jwtRepository = jwtRepository;
        this.modelMapper = modelMapper;
    }

    public TokenResponse authUser(UserRequest user) throws NotFoundException {
        UserEntity existingUser = repository.getByEmailAndPassword(user.getEmail(), user.getPassword());
        if (existingUser == null) {
            throw new NotFoundException("User not found by email and password");
        }
        return new TokenResponse(jwtRepository.getToken(existingUser));
    }

    public UserEntity addUser(UserRequest user) throws ValidationException {

        if (repository.getByEmail(user.getEmail()) == null) {
            UserEntity userEntity = modelMapper.map(user, UserEntity.class);
            this.repository.saveAndFlush(userEntity);

            return userEntity;
        }
        throw new ValidationException("User already exist with email " + user.getEmail());
    }
}
