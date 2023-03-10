package com.example.springsecurity2023;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringSecurity2023Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity2023Application.class, args);
    }

}
