package com.example.javaitmo2.service;

import com.example.javaitmo2.dto.request.UserRequest;
import com.example.javaitmo2.dto.response.TokenResponse;
import com.example.javaitmo2.repository.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void authUserException() {
        assertThrows(NotFoundException.class, () -> userService.authUser(new UserRequest("test", "test")));
    }

    @Test
    public void authUserSuccess() throws Exception {
//        userService.addUser(new UserRequest("test@mail.test", "rrrrrrr", "Natalia", "Test"));
        assertInstanceOf(TokenResponse.class, userService.authUser(new UserRequest("test@mail.test", "rrrrrrr")));
    }
}
