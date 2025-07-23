package com.mergington.highschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * High School Management System Application
 * 
 * A Spring Boot application for managing extracurricular activities
 * at Mergington High School following Clean Architecture principles.
 */
@SpringBootApplication
public class HighSchoolManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HighSchoolManagementApplication.class, args);
    }
}