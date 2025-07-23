package com.mergington.highschool.domain.usecase;

import com.mergington.highschool.domain.entity.Activity;
import com.mergington.highschool.domain.repository.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignupForActivityUseCaseTest {
    
    @Mock
    private ActivityRepository activityRepository;
    
    private SignupForActivityUseCase useCase;
    
    @BeforeEach
    void setUp() {
        useCase = new SignupForActivityUseCase(activityRepository);
    }
    
    @Test
    void shouldSignupStudentSuccessfully() {
        // Given
        String activityName = "Chess Club";
        String email = "student@example.com";
        Activity activity = new Activity(activityName, "Chess description", "Fridays", 10);
        
        when(activityRepository.findByName(activityName)).thenReturn(Optional.of(activity));
        when(activityRepository.save(any(Activity.class))).thenReturn(activity);
        
        // When
        Activity result = useCase.execute(activityName, email);
        
        // Then
        assertTrue(result.getParticipants().contains(email));
        verify(activityRepository).save(activity);
    }
    
    @Test
    void shouldThrowExceptionWhenActivityNotFound() {
        // Given
        String activityName = "Non-existent Activity";
        String email = "student@example.com";
        
        when(activityRepository.findByName(activityName)).thenReturn(Optional.empty());
        
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(activityName, email)
        );
        assertEquals("Atividade não encontrada", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenActivityNameIsNull() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(null, "student@example.com")
        );
        assertEquals("Activity name cannot be null or empty", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenActivityNameIsEmpty() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute("", "student@example.com")
        );
        assertEquals("Activity name cannot be null or empty", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenActivityIsFull() {
        // Given
        String activityName = "Chess Club";
        String email = "student@example.com";
        Activity activity = new Activity(activityName, "Chess description", "Fridays", 1);
        activity.addParticipant("existing@example.com"); // Fill to capacity
        
        when(activityRepository.findByName(activityName)).thenReturn(Optional.of(activity));
        
        // When & Then
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(activityName, email)
        );
        assertEquals("Activity is full. Maximum participants: 1", exception.getMessage());
    }
}