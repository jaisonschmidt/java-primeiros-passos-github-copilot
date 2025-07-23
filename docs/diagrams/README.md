# PlantUML Diagrams

This directory contains PlantUML diagrams that visualize the architecture of the High School Management System.

## Diagrams

### 1. System Context Diagram (`system-context.puml`)
Shows the system from a high-level perspective, including:
- Students and School Staff as users
- The High School Management System
- External systems (Web Browser, MongoDB)

### 2. Container Diagram (`container-diagram.puml`)
Shows the high-level technology choices and how containers communicate:
- Web Application (Spring Boot)
- Database (MongoDB)
- Web Browser interface

### 3. Component Diagram (`component-diagram.puml`)
Shows the internal structure of the Web Application container:
- Controllers (REST API endpoints)
- Services (Application layer)
- Use Cases (Domain layer)
- Repository implementations (Infrastructure layer)

### 4. Class Diagram (`class-diagram.puml`)
Shows the detailed class structure following Clean Architecture:
- Domain entities and interfaces
- Application services and DTOs
- Infrastructure implementations

## Viewing the Diagrams

To view these diagrams, you can:

1. **Use PlantUML Online**: Copy the content of any `.puml` file to [PlantUML Online](http://www.plantuml.com/plantuml/uml/)

2. **Use VS Code Extension**: Install the PlantUML extension for VS Code to preview diagrams directly in the editor

3. **Generate Images**: Use the PlantUML command line tool or Maven plugin to generate PNG/SVG images

## Architecture Principles

These diagrams illustrate the Clean Architecture principles:
- **Dependency Inversion**: Dependencies point inward toward the domain
- **Separation of Concerns**: Each layer has distinct responsibilities
- **Testability**: Clear boundaries enable isolated testing
- **Technology Independence**: Domain logic is isolated from frameworks