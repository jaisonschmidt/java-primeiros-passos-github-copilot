package com.mergington.highschool.infrastructure.persistence;

import com.mergington.highschool.domain.entity.Activity;
import com.mergington.highschool.domain.repository.ActivityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ActivityRepository using MongoDB.
 * Adapts the MongoDB repository to the domain repository interface.
 */
@Component
public class ActivityRepositoryImpl implements ActivityRepository {
    
    private final MongoActivityRepository mongoActivityRepository;
    
    public ActivityRepositoryImpl(MongoActivityRepository mongoActivityRepository) {
        this.mongoActivityRepository = mongoActivityRepository;
    }
    
    @Override
    public List<Activity> findAll() {
        return mongoActivityRepository.findAll();
    }
    
    @Override
    public Optional<Activity> findByName(String name) {
        return mongoActivityRepository.findByName(name);
    }
    
    @Override
    public Optional<Activity> findById(String id) {
        return mongoActivityRepository.findById(id);
    }
    
    @Override
    public Activity save(Activity activity) {
        return mongoActivityRepository.save(activity);
    }
    
    @Override
    public void deleteById(String id) {
        mongoActivityRepository.deleteById(id);
    }
    
    @Override
    public boolean existsByName(String name) {
        return mongoActivityRepository.existsByName(name);
    }
}