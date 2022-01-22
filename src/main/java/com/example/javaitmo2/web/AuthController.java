package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.request.UserRequest;
import com.example.javaitmo2.dto.response.ErrorResponse;
import com.example.javaitmo2.dto.response.ResponseInterface;
import com.example.javaitmo2.repository.NotFoundException;
import com.example.javaitmo2.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping( "/login")
    public @ResponseBody ResponseEntity<ResponseInterface> getAuthUser(@RequestBody UserRequest user) {
        try {
            return ResponseEntity.ok().body(this.service.authUser(user));
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

}