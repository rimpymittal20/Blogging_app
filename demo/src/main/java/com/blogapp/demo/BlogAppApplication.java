package com.blogapp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogAppApplication {

	public static void main(String[] args) {

		System.out.println("Hello World");
		SpringApplication.run(BlogAppApplication.class, args);
	}

}
