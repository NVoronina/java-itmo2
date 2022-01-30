package com.example.javaitmo2.repository;

import com.example.javaitmo2.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void givenStudent_whenSave_thenGetOk() throws Exception {
        UserEntity userEntity = new UserEntity("john@ttt.ru", "pppp", "John", "Doe");
        userRepository.save(userEntity);

        UserEntity userExist = userRepository.getByEmail("john@ttt.ru");
        assertEquals("John", userExist.getName());
    }
}
