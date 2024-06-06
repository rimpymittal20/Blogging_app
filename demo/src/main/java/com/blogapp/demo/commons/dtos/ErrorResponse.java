package com.blogapp.demo.commons.dtos;

import lombok.*;

@Data
@Builder
@Getter
public class ErrorResponse {
    private String message;
}
