package com.fitness.activityservice.repository;

import com.fitness.activityservice.entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity, String> {
}
