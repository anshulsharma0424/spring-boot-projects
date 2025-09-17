package com.fitness.userservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserResponse {
    private String id;
    private String email;
    // private String password; // We don't want to expose the password to the user
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
