package com.example.javaitmo2.repository;

import com.example.javaitmo2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity getByEmailAndPassword(String email, String password);

    @Query("from UserEntity u where u.email = :email")
    UserEntity getByEmail(String email);
}
