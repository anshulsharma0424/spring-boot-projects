package com.fitness.activityservice.service;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.entity.Activity;
import com.fitness.activityservice.mapper.ActivityMapper;
import com.fitness.activityservice.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    // for kafka
    private final KafkaTemplate<String, Activity> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    // for user validation
    private final UserValidationService userValidationService;

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {

        Boolean isValidUser = userValidationService.validateUser(activityRequest.getUserId());

        if (!isValidUser) throw new RuntimeException("Invalid user: " + activityRequest.getUserId());

        // The below work [ request -> entity -> response ] can be done via a mapper

        // ================================================

//        Activity activity = Activity.builder()
//                .userId(activityRequest.getUserId())
//                .activityType(activityRequest.getType())
//                .duration(activityRequest.getDuration())
//                .caloriesBurned(activityRequest.getCaloriesBurn())
//                .startTime(activityRequest.getStartTime())
//                .additionalMetrics(activityRequest.getAdditionalMetrics())
//                .build();

//        Activity savedActivity = activityRepository.save(activity);

        // ================================================

        Activity activity = activityMapper.toEntity(activityRequest);
        Activity savedActivity = activityRepository.save(activity);

        // Sending/Publishing the saved activity to kafka -> to "aiservice"

        try {
            kafkaTemplate.send(topicName, savedActivity.getUserId(), savedActivity);
        } catch (Exception e){
            e.printStackTrace();
        }

        return activityMapper.toResponse(savedActivity);
    }
}
