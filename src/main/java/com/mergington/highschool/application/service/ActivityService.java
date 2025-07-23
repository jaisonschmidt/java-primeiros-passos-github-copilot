package com.mergington.highschool.application.service;

import com.mergington.highschool.application.dto.ActivityDto;
import com.mergington.highschool.application.dto.SignupResponseDto;
import com.mergington.highschool.domain.entity.Activity;
import com.mergington.highschool.domain.usecase.GetActivitiesUseCase;
import com.mergington.highschool.domain.usecase.SignupForActivityUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Application service for activity management.
 * Orchestrates use cases and handles DTO conversions.
 */
@Service
public class ActivityService {
    
    private final GetActivitiesUseCase getActivitiesUseCase;
    private final SignupForActivityUseCase signupForActivityUseCase;
    
    public ActivityService(GetActivitiesUseCase getActivitiesUseCase, 
                          SignupForActivityUseCase signupForActivityUseCase) {
        this.getActivitiesUseCase = getActivitiesUseCase;
        this.signupForActivityUseCase = signupForActivityUseCase;
    }
    
    /**
     * Get all activities
     * @return List of activity DTOs
     */
    public List<ActivityDto> getAllActivities() {
        List<Activity> activities = getActivitiesUseCase.execute();
        return activities.stream()
                .map(ActivityDto::new)
                .collect(Collectors.toList());
    }
    
    /**
     * Sign up a student for an activity
     * @param activityName Name of the activity
     * @param email Student's email
     * @return Signup response message
     */
    public SignupResponseDto signupForActivity(String activityName, String email) {
        Activity updatedActivity = signupForActivityUseCase.execute(activityName, email);
        String message = String.format("%s inscrito(a) em %s com sucesso", email, updatedActivity.getName());
        return new SignupResponseDto(message);
    }
}