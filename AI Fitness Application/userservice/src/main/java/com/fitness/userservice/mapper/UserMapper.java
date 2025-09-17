package com.fitness.userservice.mapper;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    // Convert incoming request to User (Entity)
    public static User toEntity(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setFirstname(registerRequest.getFirstName());
        user.setLastname(registerRequest.getLastName());
        return user;
    }

    // Convert User (Entity) to outgoing response
    public static UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstname());
        userResponse.setLastName(user.getLastname());

        // Role and timestamps will be handled separately later

         userResponse.setCreatedAt(user.getCreatedAt());
         userResponse.setUpdatedAt(user.getUpdatedAt());

         return userResponse;
    }
}
