package com.mergington.highschool.infrastructure.persistence;

import com.mergington.highschool.domain.entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * MongoDB repository interface for Activity entity.
 * Extends MongoRepository to provide CRUD operations.
 */
@Repository
public interface MongoActivityRepository extends MongoRepository<Activity, String> {
    
    /**
     * Find activity by name
     * @param name Activity name
     * @return Optional containing the activity if found
     */
    Optional<Activity> findByName(String name);
    
    /**
     * Check if activity exists by name
     * @param name Activity name
     * @return true if activity exists
     */
    boolean existsByName(String name);
}