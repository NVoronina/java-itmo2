package com.example.javaitmo2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String email;
    private String password;
    private String name;
    private String surname;

    public UserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
