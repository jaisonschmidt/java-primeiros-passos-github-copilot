package com.mergington.highschool.application.dto;

import com.mergington.highschool.domain.entity.Activity;

import java.util.List;

/**
 * DTO for activity responses
 */
public class ActivityDto {
    
    private String id;
    private String name;
    private String description;
    private String schedule;
    private int maxParticipants;
    private List<String> participants;
    private int availableSpots;
    
    public ActivityDto() {}
    
    public ActivityDto(Activity activity) {
        this.id = activity.getId();
        this.name = activity.getName();
        this.description = activity.getDescription();
        this.schedule = activity.getSchedule();
        this.maxParticipants = activity.getMaxParticipants();
        this.participants = activity.getParticipants();
        this.availableSpots = activity.getAvailableSpots();
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
        return participants;
    }
    
    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }
    
    public int getAvailableSpots() {
        return availableSpots;
    }
    
    public void setAvailableSpots(int availableSpots) {
        this.availableSpots = availableSpots;
    }
}