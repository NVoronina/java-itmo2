package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.request.UserRequest;
import com.example.javaitmo2.dto.response.UserResponse;
import com.example.javaitmo2.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Tag(name = "User", description = "Methods for users entities")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok().body(
                modelMapper.map(this.userService.addUser(userRequest), UserResponse.class));
    }
}
