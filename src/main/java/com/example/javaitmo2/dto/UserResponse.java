package com.example.javaitmo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String uuid;
}
