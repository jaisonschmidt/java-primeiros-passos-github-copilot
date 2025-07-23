package com.mergington.highschool.application.service;

import com.mergington.highschool.application.dto.ActivityDto;
import com.mergington.highschool.application.dto.SignupResponseDto;
import com.mergington.highschool.domain.entity.Activity;
import com.mergington.highschool.domain.usecase.GetActivitiesUseCase;
import com.mergington.highschool.domain.usecase.SignupForActivityUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {
    
    @Mock
    private GetActivitiesUseCase getActivitiesUseCase;
    
    @Mock
    private SignupForActivityUseCase signupForActivityUseCase;
    
    private ActivityService activityService;
    
    @BeforeEach
    void setUp() {
        activityService = new ActivityService(getActivitiesUseCase, signupForActivityUseCase);
    }
    
    @Test
    void shouldReturnAllActivitiesAsDto() {
        // Given
        Activity activity1 = new Activity("Chess Club", "Chess description", "Fridays", 10);
        Activity activity2 = new Activity("Programming", "Programming description", "Tuesdays", 15);
        List<Activity> activities = Arrays.asList(activity1, activity2);
        
        when(getActivitiesUseCase.execute()).thenReturn(activities);
        
        // When
        List<ActivityDto> result = activityService.getAllActivities();
        
        // Then
        assertEquals(2, result.size());
        assertEquals("Chess Club", result.get(0).getName());
        assertEquals("Programming", result.get(1).getName());
    }
    
    @Test
    void shouldSignupStudentAndReturnSuccessMessage() {
        // Given
        String activityName = "Chess Club";
        String email = "student@example.com";
        Activity activity = new Activity(activityName, "Chess description", "Fridays", 10);
        activity.addParticipant(email);
        
        when(signupForActivityUseCase.execute(activityName, email)).thenReturn(activity);
        
        // When
        SignupResponseDto result = activityService.signupForActivity(activityName, email);
        
        // Then
        assertEquals("student@example.com inscrito(a) em Chess Club com sucesso", result.getMessage());
    }
}