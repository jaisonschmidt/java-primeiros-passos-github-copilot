package com.mergington.highschool.domain.usecase;

import com.mergington.highschool.domain.entity.Activity;
import com.mergington.highschool.domain.repository.ActivityRepository;

import java.util.List;

/**
 * Use case for retrieving activities.
 * Implements business logic for activity retrieval operations.
 */
public class GetActivitiesUseCase {
    
    private final ActivityRepository activityRepository;
    
    public GetActivitiesUseCase(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
    
    /**
     * Execute the use case to get all activities
     * @return List of all activities
     */
    public List<Activity> execute() {
        return activityRepository.findAll();
    }
}