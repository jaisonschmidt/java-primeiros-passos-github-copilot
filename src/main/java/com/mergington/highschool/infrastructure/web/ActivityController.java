package com.mergington.highschool.infrastructure.web;

import com.mergington.highschool.application.dto.ActivityDto;
import com.mergington.highschool.application.dto.SignupRequestDto;
import com.mergington.highschool.application.dto.SignupResponseDto;
import com.mergington.highschool.application.service.ActivityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for activity management endpoints.
 * Handles HTTP requests and delegates to application services.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow frontend to access the API
public class ActivityController {
    
    private final ActivityService activityService;
    
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }
    
    /**
     * Get all activities
     * @return List of activities
     */
    @GetMapping("/activities")
    public ResponseEntity<List<ActivityDto>> getActivities() {
        List<ActivityDto> activities = activityService.getAllActivities();
        return ResponseEntity.ok(activities);
    }
    
    /**
     * Sign up a student for an activity
     * @param activityName Activity name
     * @param signupRequest Signup request with email
     * @return Signup response message
     */
    @PostMapping("/activities/{activityName}/signup")
    public ResponseEntity<SignupResponseDto> signupForActivity(
            @PathVariable String activityName,
            @Valid @RequestBody SignupRequestDto signupRequest) {
        
        try {
            SignupResponseDto response = activityService.signupForActivity(activityName, signupRequest.getEmail());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new SignupResponseDto("Erro: " + e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest()
                    .body(new SignupResponseDto("Erro: " + e.getMessage()));
        }
    }
}