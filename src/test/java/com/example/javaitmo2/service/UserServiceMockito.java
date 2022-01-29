package com.example.javaitmo2.service;

import com.example.javaitmo2.dto.request.UserRequest;
import com.example.javaitmo2.dto.response.TokenResponse;
import com.example.javaitmo2.dto.response.UserResponse;
import com.example.javaitmo2.repository.JwtRepository;
import com.example.javaitmo2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(MockitoExtension.class)
public class UserServiceMockito {

    @Mock
    UserRepository userRepository;

    @Mock
    JwtRepository jwtRepository;

//    @Test
//    public void testMockitoUser() throws Exception {
//        Mockito.when(userRepository.getByLoginPassword("test@mail.test", "rrrrrrr"))
//                .thenReturn(new UserResponse(
//                "test@mail.test",
//                "rrrrrrr",
//                "Nataly",
//                "Best",
//                UUID.randomUUID().toString()
//        ));
//        UserService userService = new UserService(userRepository, jwtRepository);
//        assertInstanceOf(TokenResponse.class, userService.authUser(new UserRequest("test@mail.test", "rrrrrrr")));
//    }
}
