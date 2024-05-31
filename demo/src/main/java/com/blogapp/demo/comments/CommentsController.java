package com.blogapp.demo.comments;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles/{article-slug}/comments")
public class CommentsController {


    @GetMapping("")
    String getComments(){
        return "comments";
    }
}
