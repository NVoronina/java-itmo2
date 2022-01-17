package com.example.javaitmo2.repository;

import com.example.javaitmo2.dto.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public class UserRepository {

    public ArrayList<User> getList() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(
                "test@mail.test",
                "rrrrrrr",
                "Nataly",
                "Best",
                UUID.randomUUID().toString()
        ));

        return userList;
    }

    public User getByLoginPassword(String email, String password) throws NotFoundException {
        ArrayList<User> userList = this.getList();
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }

        throw new NotFoundException("User not found by login and password");
    }
}
