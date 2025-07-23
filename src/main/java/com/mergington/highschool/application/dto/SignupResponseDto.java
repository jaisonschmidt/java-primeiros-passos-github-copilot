package com.mergington.highschool.application.dto;

/**
 * DTO for signup response messages
 */
public class SignupResponseDto {
    
    private String message;
    
    public SignupResponseDto() {}
    
    public SignupResponseDto(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}