package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.Token;
import com.example.javaitmo2.dto.User;
import com.example.javaitmo2.repository.NotFoundException;
import com.example.javaitmo2.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping( "/login")
    public @ResponseBody Token getAuthUser(@RequestBody User user) {
        try {
            return this.service.authUser(user);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}