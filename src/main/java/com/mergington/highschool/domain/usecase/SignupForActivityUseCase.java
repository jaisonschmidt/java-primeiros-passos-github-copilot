package com.mergington.highschool.domain.usecase;

import com.mergington.highschool.domain.entity.Activity;
import com.mergington.highschool.domain.repository.ActivityRepository;

/**
 * Use case for student signup to activities.
 * Implements business logic for activity registration operations.
 */
public class SignupForActivityUseCase {
    
    private final ActivityRepository activityRepository;
    
    public SignupForActivityUseCase(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
    
    /**
     * Execute the use case to sign up a student for an activity
     * @param activityName Name of the activity
     * @param email Student's email
     * @return Updated activity
     * @throws IllegalArgumentException if activity not found or email is invalid
     * @throws IllegalStateException if activity is full or student already registered
     */
    public Activity execute(String activityName, String email) {
        if (activityName == null || activityName.trim().isEmpty()) {
            throw new IllegalArgumentException("Activity name cannot be null or empty");
        }
        
        Activity activity = activityRepository.findByName(activityName)
                .orElseThrow(() -> new IllegalArgumentException("Atividade não encontrada"));
        
        // Business logic is handled by the entity
        activity.addParticipant(email);
        
        return activityRepository.save(activity);
    }
}