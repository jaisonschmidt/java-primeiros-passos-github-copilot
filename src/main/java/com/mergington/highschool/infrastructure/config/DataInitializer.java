package com.mergington.highschool.infrastructure.config;

import com.mergington.highschool.domain.entity.Activity;
import com.mergington.highschool.domain.repository.ActivityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Component to initialize the database with sample activities.
 * Runs at application startup.
 */
@Component
public class DataInitializer implements CommandLineRunner {
    
    private final ActivityRepository activityRepository;
    
    public DataInitializer(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
    
    @Override
    public void run(String... args) {
        // Only initialize if no activities exist
        if (activityRepository.findAll().isEmpty()) {
            initializeActivities();
        }
    }
    
    private void initializeActivities() {
        // Create Chess Club
        Activity chessClub = new Activity(
                "Clube de Xadrez",
                "Aprenda estratégias e participe de torneios de xadrez",
                "Sextas, 15h30 - 17h",
                12
        );
        chessClub.setParticipants(Arrays.asList("michael@mergington.edu", "daniel@mergington.edu"));
        
        // Create Programming Class
        Activity programmingClass = new Activity(
                "Aula de Programação",
                "Aprenda fundamentos de programação e desenvolva projetos de software",
                "Terças e quintas, 15h30 - 16h30",
                20
        );
        programmingClass.setParticipants(Arrays.asList("emma@mergington.edu", "sophia@mergington.edu"));
        
        // Create Physical Education
        Activity physicalEducation = new Activity(
                "Educação Física",
                "Educação física e atividades esportivas",
                "Segundas, quartas e sextas, 14h - 15h",
                30
        );
        physicalEducation.setParticipants(Arrays.asList("john@mergington.edu", "olivia@mergington.edu"));
        
        // Save activities
        activityRepository.save(chessClub);
        activityRepository.save(programmingClass);
        activityRepository.save(physicalEducation);
        
        System.out.println("Database initialized with sample activities");
    }
}