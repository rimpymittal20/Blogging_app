package com.blogapp.demo.users;

import com.blogapp.demo.user.UserEntity;
import com.blogapp.demo.user.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")

public class UsersRepositoryTests {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Order(1)
    void can_create_users(){
        var user =UserEntity.builder()
                .username("Rimpy Mittal")
                .email("rimpymittal212@gmail.com")
                .build();

        usersRepository.save(user);

    }

    @Test
    @Order(4)
    //Every test runs on a fresh database instance
    void can_find_users(){
        var user =UserEntity.builder()
                .username("Rimpy Mittal")
                .email("rimpymittal212@gmail.com")
                .build();


        usersRepository.save(user);
        var users=usersRepository.findAll();
        System.out.println("Hello Rimpy Mittal How are you?"+users);
        Assertions.assertEquals(1, users.size());

    }
}
