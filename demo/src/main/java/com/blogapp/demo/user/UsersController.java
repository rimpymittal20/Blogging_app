package com.blogapp.demo.user;


import com.blogapp.demo.user.dtos.CreateUserRequest;
import com.blogapp.demo.user.dtos.LoginUserRequest;
import com.blogapp.demo.user.dtos.UserResponse;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService userService;
    private final ModelMapper modelMapper;
    public UsersController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

//    @PostMapping("")
//    void signUpUser(@RequestBody CreateUserRequest req){
//        userService.createUser(req);
//    }

    @PostMapping("")
    ResponseEntity<UserResponse> signUpUser(@RequestBody CreateUserRequest request)
    {
        UserEntity savedUser=userService.createUser(request);
        URI savedUserUrl=URI.create("/users"+savedUser.getId());

        return ResponseEntity.created(savedUserUrl).body(modelMapper.map(savedUser, UserResponse.class));
    }

    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request){
        UserEntity savedUser=userService.loginUser(request.getUsername(), request.getPassword());

        return ResponseEntity.ok(modelMapper.map(savedUser, UserResponse.class));
    }
}
