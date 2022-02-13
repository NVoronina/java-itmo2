package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.request.UserRequest;
import com.example.javaitmo2.dto.response.ResponseInterface;
import com.example.javaitmo2.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @Tag(name = "User", description = "Methods for users entities")
    @PostMapping( "/login")
    public @ResponseBody ResponseEntity<ResponseInterface> getAuthUser(@RequestBody UserRequest user) {
        return ResponseEntity.ok().body(service.authUser(user));
    }

}