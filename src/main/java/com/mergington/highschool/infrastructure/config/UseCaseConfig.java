package com.mergington.highschool.infrastructure.config;

import com.mergington.highschool.domain.repository.ActivityRepository;
import com.mergington.highschool.domain.usecase.GetActivitiesUseCase;
import com.mergington.highschool.domain.usecase.SignupForActivityUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for dependency injection of use cases.
 * Follows Clean Architecture by injecting domain dependencies.
 */
@Configuration
public class UseCaseConfig {
    
    @Bean
    public GetActivitiesUseCase getActivitiesUseCase(ActivityRepository activityRepository) {
        return new GetActivitiesUseCase(activityRepository);
    }
    
    @Bean
    public SignupForActivityUseCase signupForActivityUseCase(ActivityRepository activityRepository) {
        return new SignupForActivityUseCase(activityRepository);
    }
}