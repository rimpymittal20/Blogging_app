package com.blogapp.demo.users;

import com.blogapp.demo.user.UserService;
import com.blogapp.demo.user.dtos.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UsersServiceTests {

    @Autowired
    UserService userService;

    @Test
    void can_create_user(){
        var user=userService.createUser(new CreateUserRequest("Rimpy","123456", "rimpymittal212@gmail.com"));


        System.out.println("Printing user: "+user);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Rimpy", user.getUsername());
    }
}
