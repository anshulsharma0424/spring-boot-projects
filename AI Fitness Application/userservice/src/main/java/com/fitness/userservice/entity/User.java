package com.fitness.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data // for generating getters and setters
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private String id;

    @Column(nullable = false,  unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;
    
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER; // by default is "USER"

    // Create and update timestamps will be generated automatically
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
