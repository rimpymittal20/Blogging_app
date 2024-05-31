package com.blogapp.demo.user.dtos;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserResponse {
    private long id;
    private String username;
    private String email;
    private String bio;
    private String image;
}
