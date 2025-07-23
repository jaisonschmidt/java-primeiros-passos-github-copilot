# High School Management System - C4 Model Documentation

## System Context (Level 1)

The High School Management System is a web application that allows students to view and sign up for extracurricular activities at Mergington High School.

### Actors
- **Students**: Register for extracurricular activities
- **School Staff**: View activity registrations (future enhancement)

### External Systems
- **MongoDB Database**: Stores activity and registration data
- **Web Browser**: User interface for accessing the system

## Container Diagram (Level 2)

### Containers
1. **Web Application** (Spring Boot)
   - Serves static web pages
   - Provides REST API endpoints
   - Handles business logic

2. **Database** (MongoDB)
   - Stores activity information
   - Stores student registrations

## Component Diagram (Level 3)

### Application Components

#### Domain Layer (Core Business Logic)
- **Activity Entity**: Represents extracurricular activities with business rules
- **ActivityRepository Interface**: Defines data access contract
- **GetActivitiesUseCase**: Business logic for retrieving activities
- **SignupForActivityUseCase**: Business logic for student registration

#### Application Layer (Orchestration)
- **ActivityService**: Orchestrates use cases and handles DTO conversions
- **DTOs**: Data transfer objects for API communication

#### Infrastructure Layer (Technical Implementation)
- **ActivityController**: REST API endpoints
- **StaticController**: Serves static web content
- **ActivityRepositoryImpl**: MongoDB implementation of repository
- **MongoActivityRepository**: Spring Data MongoDB repository
- **Configuration Classes**: Dependency injection and application setup

## Architecture Principles

### Clean Architecture
The system follows Clean Architecture principles:
- **Dependency Inversion**: Dependencies point inward toward the domain
- **Separation of Concerns**: Each layer has distinct responsibilities
- **Technology Independence**: Domain logic is isolated from frameworks

### Layers
1. **Domain Layer**: Contains business entities and rules
2. **Application Layer**: Orchestrates use cases
3. **Infrastructure Layer**: Implements technical details

## Quality Attributes

- **Testability**: Each layer can be tested in isolation
- **Maintainability**: Clear separation of concerns
- **Flexibility**: Easy to change technical implementations
- **Reliability**: Comprehensive test coverage with JUnit 5 and Mockito