package com.example.javaitmo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String uuid;
}
