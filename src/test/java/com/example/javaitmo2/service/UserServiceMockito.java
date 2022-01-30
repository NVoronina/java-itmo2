package com.example.javaitmo2.service;

import com.example.javaitmo2.dto.request.UserRequest;
import com.example.javaitmo2.dto.response.TokenResponse;
import com.example.javaitmo2.dto.response.UserResponse;
import com.example.javaitmo2.entity.UserEntity;
import com.example.javaitmo2.repository.JwtRepository;
import com.example.javaitmo2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(MockitoExtension.class)
public class UserServiceMockito {

    @Mock
    UserRepository userRepository;

    @Mock
    JwtRepository jwtRepository;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testMockitoUser() throws Exception {
        Mockito.when(userRepository.getByEmailAndPassword("test@mail.test", "rrrrrrr"))
                .thenReturn(new UserEntity(
                "test@mail.test",
                "rrrrrrr",
                "Nataly",
                "Best"
        ));
        UserService userService = new UserService(userRepository, jwtRepository, modelMapper);
        assertInstanceOf(TokenResponse.class, userService.authUser(new UserRequest(
                "test@mail.test",
                "rrrrrrr",
                "Nataly",
                "Best")
        ));
    }
}
