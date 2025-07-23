package com.mergington.highschool.domain.repository;

import com.mergington.highschool.domain.entity.Activity;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Activity entity following Clean Architecture principles.
 * This interface defines the contract for data access without specifying implementation details.
 */
public interface ActivityRepository {
    
    /**
     * Find all activities
     * @return List of all activities
     */
    List<Activity> findAll();
    
    /**
     * Find activity by name
     * @param name Activity name
     * @return Optional containing the activity if found
     */
    Optional<Activity> findByName(String name);
    
    /**
     * Find activity by ID
     * @param id Activity ID
     * @return Optional containing the activity if found
     */
    Optional<Activity> findById(String id);
    
    /**
     * Save an activity
     * @param activity Activity to save
     * @return Saved activity with generated ID if new
     */
    Activity save(Activity activity);
    
    /**
     * Delete activity by ID
     * @param id Activity ID
     */
    void deleteById(String id);
    
    /**
     * Check if activity exists by name
     * @param name Activity name
     * @return true if activity exists
     */
    boolean existsByName(String name);
}