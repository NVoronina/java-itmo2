package com.example.javaitmo2.repository;

import com.example.javaitmo2.dto.response.UserResponse;
import com.example.javaitmo2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity getByEmailAndPassword(String email, String password) throws NotFoundException;
    UserEntity getByEmail(String email);

//    public ArrayList<UserResponse> getList() {
//        ArrayList<UserResponse> userList = new ArrayList<>();
//        userList.add(new UserResponse(
//                "test@mail.test",
//                "rrrrrrr",
//                "Nataly",
//                "Best",
//                UUID.randomUUID().toString()
//        ));
//
//        return userList;
//    }
//
//    public ArrayList<UserResponse> addUser() {
//        ArrayList<UserResponse> userList = new ArrayList<>();
//        userList.add(new UserResponse(
//                "test@mail.test",
//                "rrrrrrr",
//                "Nataly",
//                "Best",
//                UUID.randomUUID().toString()
//        ));
//
//        return userList;
//    }
//
//    public UserResponse getByLoginPassword(String email, String password) throws NotFoundException {
//        ArrayList<UserResponse> userList = this.getList();
//        for (UserResponse user : userList) {
//            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
//                return user;
//            }
//        }
//
//        throw new NotFoundException("User not found by login and password");
//    }
}
