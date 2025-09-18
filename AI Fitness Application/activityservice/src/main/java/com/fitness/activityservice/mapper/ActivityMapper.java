package com.fitness.activityservice.mapper;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.entity.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {
    // Convert incoming ActivityRequest to Activity (Entity)
    public Activity toEntity(ActivityRequest activityRequest) {
        return Activity.builder()
                .userId(activityRequest.getUserId())
                .activityType(activityRequest.getType())
                .duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurn())
                .startTime(activityRequest.getStartTime())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .build();
    }

    // Convert Activity (Entity) to outgoing ActivityResponse
    public ActivityResponse toResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setType(activity.getActivityType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurn(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }
}
