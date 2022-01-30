package com.example.javaitmo2.repository;

import com.example.javaitmo2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity getByEmailAndPassword(String email, String password);
    UserEntity getByEmail(String email);
}
