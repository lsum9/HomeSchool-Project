package com.home_school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HomeSchoolApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeSchoolApplication.class, args);
    }

}
