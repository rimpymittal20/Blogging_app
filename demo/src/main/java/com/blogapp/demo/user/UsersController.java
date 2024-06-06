package com.blogapp.demo.user;


import com.blogapp.demo.user.dtos.CreateUserRequest;
import com.blogapp.demo.user.dtos.LoginUserRequest;
import com.blogapp.demo.user.dtos.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import com.blogapp.demo.commons.dtos.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;


@ControllerAdvice
@RestController
@RequestMapping("/users")
public class UsersController{

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

    @ExceptionHandler({UserService.UserNotFoundException.class,UserService.InvalidCredentialsException.class})

    ResponseEntity<ErrorResponse> handleUserExceptions(Exception ex)
    {
        String message;
        HttpStatus status;
        if(ex instanceof UserService.UserNotFoundException)
        {
            message=ex.getMessage();
            status=HttpStatus.NOT_FOUND;
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body(ErrorResponse.builder()
//                            .message(ex.getMessage())
//                            .build());
            //instead of this we are returning dynamic response in line 88
        }
        else if (ex instanceof UserService.InvalidCredentialsException) {
            message=ex.getMessage();
            status=HttpStatus.UNAUTHORIZED;
        }
        else{
            message="Something went wrong";
            status=HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse response= ErrorResponse.builder()
                .message(message)
                .build();

        return ResponseEntity.status(status).body(response);
    }

}
