package com.mergington.highschool.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Activity domain entity representing an extracurricular activity.
 * This entity encapsulates the business rules and behavior of an activity.
 */
@Document(collection = "activities")
public class Activity {
    
    @Id
    private String id;
    
    private String name;
    private String description;
    private String schedule;
    private int maxParticipants;
    private List<String> participants;
    
    // Default constructor for MongoDB
    public Activity() {
        this.participants = new ArrayList<>();
    }
    
    public Activity(String name, String description, String schedule, int maxParticipants) {
        this.name = name;
        this.description = description;
        this.schedule = schedule;
        this.maxParticipants = maxParticipants;
        this.participants = new ArrayList<>();
    }
    
    /**
     * Business logic: Add a participant to the activity
     * @param email Student's email
     * @throws IllegalStateException if activity is full
     * @throws IllegalArgumentException if email is null or empty
     */
    public void addParticipant(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        
        if (participants.size() >= maxParticipants) {
            throw new IllegalStateException("Activity is full. Maximum participants: " + maxParticipants);
        }
        
        if (participants.contains(email)) {
            throw new IllegalArgumentException("Student is already registered for this activity");
        }
        
        participants.add(email);
    }
    
    /**
     * Business logic: Check if activity has available spots
     */
    public boolean hasAvailableSpots() {
        return participants.size() < maxParticipants;
    }
    
    /**
     * Business logic: Get number of available spots
     */
    public int getAvailableSpots() {
        return maxParticipants - participants.size();
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getSchedule() {
        return schedule;
    }
    
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    
    public int getMaxParticipants() {
        return maxParticipants;
    }
    
    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
    
    public List<String> getParticipants() {
        return new ArrayList<>(participants); // Return defensive copy
    }
    
    public void setParticipants(List<String> participants) {
        this.participants = participants != null ? new ArrayList<>(participants) : new ArrayList<>();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id) && Objects.equals(name, activity.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    
    @Override
    public String toString() {
        return "Activity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", schedule='" + schedule + '\'' +
                ", maxParticipants=" + maxParticipants +
                ", participants=" + participants.size() + "/" + maxParticipants +
                '}';
    }
}