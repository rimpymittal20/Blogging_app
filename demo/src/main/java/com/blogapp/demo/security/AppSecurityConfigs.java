package com.blogapp.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfigs {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET,"/*").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/h2-console/**").permitAll())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
//                .headers(headers -> headers.frameOptions().sameOrigin())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())) // This allows H2 console frames to be displayed
                .build();
    }


}
