package com.mergington.highschool.domain.usecase;

import com.mergington.highschool.domain.entity.Activity;
import com.mergington.highschool.domain.repository.ActivityRepository;
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
class GetActivitiesUseCaseTest {
    
    @Mock
    private ActivityRepository activityRepository;
    
    private GetActivitiesUseCase useCase;
    
    @BeforeEach
    void setUp() {
        useCase = new GetActivitiesUseCase(activityRepository);
    }
    
    @Test
    void shouldReturnAllActivities() {
        // Given
        Activity activity1 = new Activity("Chess Club", "Chess description", "Fridays", 10);
        Activity activity2 = new Activity("Programming", "Programming description", "Tuesdays", 15);
        List<Activity> expectedActivities = Arrays.asList(activity1, activity2);
        
        when(activityRepository.findAll()).thenReturn(expectedActivities);
        
        // When
        List<Activity> actualActivities = useCase.execute();
        
        // Then
        assertEquals(expectedActivities, actualActivities);
        assertEquals(2, actualActivities.size());
    }
}