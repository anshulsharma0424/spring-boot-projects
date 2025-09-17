package com.fitness.userservice.service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.entity.User;
import com.fitness.userservice.exception.EmailAlreadyExistsException;
import com.fitness.userservice.exception.UserNotFoundException;
import com.fitness.userservice.mapper.UserMapper;
import com.fitness.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse register(RegisterRequest registerRequest) {
        // Set the fields we are getting in "RegisterUserRequest" to "UserResponse"

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        User user = UserMapper.toEntity(registerRequest);
        User savedUser =  userRepository.save(user);
        return UserMapper.toResponse(savedUser);

        // The below conversion of request(incoming) -> User -> Response(Outgoing) can be done separately in 'mapper' package

//        User user = new User();
//        user.setEmail(registerRequest.getEmail());
//        user.setPassword(registerRequest.getPassword());
//        user.setFirstname(registerRequest.getFirstName());
//        user.setLastname(registerRequest.getLastName());

//        User savedUser = userRepository.save(user);

//        UserResponse userResponse = new UserResponse();

//        userResponse.setId(savedUser.getId());
//        userResponse.setEmail(savedUser.getEmail());
//        userResponse.setFirstName(savedUser.getFirstname());
//        userResponse.setLastName(savedUser.getLastname());
//        userResponse.setCreatedAt(savedUser.getCreatedAt());
//        userResponse.setUpdatedAt(savedUser.getUpdatedAt());

//        return userResponse;
    }

    public UserResponse getUserById(String id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with UserID: " + id + " doesn't exist"));
        return UserMapper.toResponse(user);
    }
}
