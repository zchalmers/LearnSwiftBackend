# LearnSwift Backend Service

A robust Spring Boot backend service designed to support the LearnSwift application. This service manages content delivery, file system interactions, and a structured data repository for educational content.

## Project Overview
The LearnSwift Backend serves as the central API for the LearnSwift ecosystem. It provides endpoints for managing food-related data models (categories, items, and recipes) and includes a specialized file management system for handling application assets.

### Key Features
- RESTful API Architecture: Built with Spring Boot for high performance and scalability.
- Dual Database Support: Compatible with both H2 (for development) and MySQL (for production).
- Role-Based Data Models: Structured repositories for Food Categories, Items, and Recipes.
- File Management System: Dedicated controllers for file uploads and system interactions.
- Spring Security: Integrated security layer for protected endpoints.

## Technical Stack
- Java: 17
- Framework: Spring Boot 3.2.5
- Build Tool: Gradle
- Data Persistence: Spring Data JPA / Hibernate
- Databases: MySQL 8, H2 (In-memory)

## Getting Started
### Prerequisites
- Java JDK 17
- MySQL Server (optional, for production profile)
- Gradle (included via gradlew)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/zchalmers/LearnSwiftBackend.git
   ```
2. Configure the database in `src/main/resources/application.properties` (if using MySQL).

### Running the Application
To run the application locally using the Gradle wrapper:
```bash
./gradlew bootRun
```

To build a standalone executable JAR:
```bash
./gradlew build
```
The resulting JAR will be located in `build/libs/`.

## API Endpoints
- /api/food: Access categories, items, and recipes.
- /api/files: Endpoint for file system operations and asset management.

## Contributors
- Zachary Chalmers (zchalmer@asu.edu)

## Acknowledgments
Developed as part of the LearnSwift educational project suite.
