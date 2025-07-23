package com.mergington.highschool.infrastructure.persistence;

import com.mergington.highschool.domain.entity.Activity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@Testcontainers
class MongoActivityRepositoryTest {
    
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0");
    
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
    
    @Autowired
    private MongoActivityRepository repository;
    
    @Test
    void shouldSaveAndFindActivity() {
        // Given
        Activity activity = new Activity("Test Activity", "Test Description", "Test Schedule", 10);
        
        // When
        Activity saved = repository.save(activity);
        
        // Then
        assertNotNull(saved.getId());
        assertEquals("Test Activity", saved.getName());
        
        Optional<Activity> found = repository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Test Activity", found.get().getName());
    }
    
    @Test
    void shouldFindByName() {
        // Given
        Activity activity = new Activity("Chess Club", "Chess description", "Fridays", 10);
        repository.save(activity);
        
        // When
        Optional<Activity> found = repository.findByName("Chess Club");
        
        // Then
        assertTrue(found.isPresent());
        assertEquals("Chess Club", found.get().getName());
    }
    
    @Test
    void shouldReturnEmptyWhenActivityNotFound() {
        // When
        Optional<Activity> found = repository.findByName("Non-existent Activity");
        
        // Then
        assertFalse(found.isPresent());
    }
    
    @Test
    void shouldCheckIfActivityExistsByName() {
        // Given
        Activity activity = new Activity("Programming", "Programming description", "Tuesdays", 15);
        repository.save(activity);
        
        // When & Then
        assertTrue(repository.existsByName("Programming"));
        assertFalse(repository.existsByName("Non-existent"));
    }
}