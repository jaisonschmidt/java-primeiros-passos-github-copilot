package com.mergington.highschool.infrastructure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mergington.highschool.application.dto.ActivityDto;
import com.mergington.highschool.application.dto.SignupRequestDto;
import com.mergington.highschool.application.dto.SignupResponseDto;
import com.mergington.highschool.application.service.ActivityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ActivityController.class)
class ActivityControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ActivityService activityService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void shouldReturnAllActivities() throws Exception {
        // Given
        ActivityDto activity1 = new ActivityDto();
        activity1.setName("Chess Club");
        activity1.setDescription("Chess description");
        
        ActivityDto activity2 = new ActivityDto();
        activity2.setName("Programming");
        activity2.setDescription("Programming description");
        
        List<ActivityDto> activities = Arrays.asList(activity1, activity2);
        when(activityService.getAllActivities()).thenReturn(activities);
        
        // When & Then
        mockMvc.perform(get("/api/activities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Chess Club"))
                .andExpect(jsonPath("$[1].name").value("Programming"));
    }
    
    @Test
    void shouldSignupStudentSuccessfully() throws Exception {
        // Given
        String activityName = "Chess Club";
        SignupRequestDto request = new SignupRequestDto("student@example.com");
        SignupResponseDto response = new SignupResponseDto("student@example.com inscrito(a) em Chess Club com sucesso");
        
        when(activityService.signupForActivity(activityName, request.getEmail())).thenReturn(response);
        
        // When & Then
        mockMvc.perform(post("/api/activities/{activityName}/signup", activityName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("student@example.com inscrito(a) em Chess Club com sucesso"));
    }
    
    @Test
    void shouldReturnBadRequestForInvalidEmail() throws Exception {
        // Given
        String activityName = "Chess Club";
        SignupRequestDto request = new SignupRequestDto("invalid-email");
        
        // When & Then
        mockMvc.perform(post("/api/activities/{activityName}/signup", activityName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void shouldHandleServiceExceptions() throws Exception {
        // Given
        String activityName = "Non-existent Activity";
        SignupRequestDto request = new SignupRequestDto("student@example.com");
        
        when(activityService.signupForActivity(activityName, request.getEmail()))
                .thenThrow(new IllegalArgumentException("Activity not found"));
        
        // When & Then
        mockMvc.perform(post("/api/activities/{activityName}/signup", activityName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Erro: Activity not found"));
    }
}