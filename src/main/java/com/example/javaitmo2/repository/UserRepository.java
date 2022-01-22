package com.example.javaitmo2.repository;

import com.example.javaitmo2.dto.response.UserResponse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public class UserRepository {

    public ArrayList<UserResponse> getList() {
        ArrayList<UserResponse> userList = new ArrayList<>();
        userList.add(new UserResponse(
                "test@mail.test",
                "rrrrrrr",
                "Nataly",
                "Best",
                UUID.randomUUID().toString()
        ));

        return userList;
    }

    public UserResponse getByLoginPassword(String email, String password) throws NotFoundException {
        ArrayList<UserResponse> userList = this.getList();
        for (UserResponse user : userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }

        throw new NotFoundException("User not found by login and password");
    }
}
