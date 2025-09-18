package com.fitness.activityservice.dto;

import com.fitness.activityservice.entity.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ActivityResponse {
    private String id; // activity ID
    private String userId; // userID associated with activity
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurn;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
