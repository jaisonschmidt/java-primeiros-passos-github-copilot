package com.mergington.highschool.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {
    
    private Activity activity;
    
    @BeforeEach
    void setUp() {
        activity = new Activity("Test Activity", "Test Description", "Test Schedule", 3);
    }
    
    @Test
    void shouldCreateActivityWithCorrectProperties() {
        assertEquals("Test Activity", activity.getName());
        assertEquals("Test Description", activity.getDescription());
        assertEquals("Test Schedule", activity.getSchedule());
        assertEquals(3, activity.getMaxParticipants());
        assertTrue(activity.getParticipants().isEmpty());
    }
    
    @Test
    void shouldAddParticipantSuccessfully() {
        String email = "test@example.com";
        
        activity.addParticipant(email);
        
        assertEquals(1, activity.getParticipants().size());
        assertTrue(activity.getParticipants().contains(email));
    }
    
    @Test
    void shouldThrowExceptionWhenAddingNullEmail() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> activity.addParticipant(null)
        );
        assertEquals("Email cannot be null or empty", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenAddingEmptyEmail() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> activity.addParticipant("")
        );
        assertEquals("Email cannot be null or empty", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenActivityIsFull() {
        // Fill activity to capacity
        activity.addParticipant("user1@example.com");
        activity.addParticipant("user2@example.com");
        activity.addParticipant("user3@example.com");
        
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> activity.addParticipant("user4@example.com")
        );
        assertEquals("Activity is full. Maximum participants: 3", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenAddingDuplicateParticipant() {
        String email = "test@example.com";
        activity.addParticipant(email);
        
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> activity.addParticipant(email)
        );
        assertEquals("Student is already registered for this activity", exception.getMessage());
    }
    
    @Test
    void shouldReturnTrueWhenActivityHasAvailableSpots() {
        assertTrue(activity.hasAvailableSpots());
        
        activity.addParticipant("user1@example.com");
        assertTrue(activity.hasAvailableSpots());
        
        activity.addParticipant("user2@example.com");
        assertTrue(activity.hasAvailableSpots());
        
        activity.addParticipant("user3@example.com");
        assertFalse(activity.hasAvailableSpots());
    }
    
    @Test
    void shouldReturnCorrectNumberOfAvailableSpots() {
        assertEquals(3, activity.getAvailableSpots());
        
        activity.addParticipant("user1@example.com");
        assertEquals(2, activity.getAvailableSpots());
        
        activity.addParticipant("user2@example.com");
        assertEquals(1, activity.getAvailableSpots());
        
        activity.addParticipant("user3@example.com");
        assertEquals(0, activity.getAvailableSpots());
    }
    
    @Test
    void shouldReturnDefensiveCopyOfParticipants() {
        activity.addParticipant("user1@example.com");
        
        var participants = activity.getParticipants();
        participants.add("hacker@example.com");
        
        // Original list should not be modified
        assertEquals(1, activity.getParticipants().size());
        assertFalse(activity.getParticipants().contains("hacker@example.com"));
    }
}