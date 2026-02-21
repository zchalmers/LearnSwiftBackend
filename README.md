# LearnSwift Backend API

A Spring Boot REST API backend for the [LearnSwift](https://github.com/zchalmers/LearnSwift) iOS application. This service provides food categories, items, and recipe data through RESTful endpoints with H2 or MySQL database support.

## Project Overview

This backend serves as the data layer for the LearnSwift iOS app, providing:
- Food category listings
- Food items organized by category
- Detailed recipe information with ingredients and instructions
- RESTful API endpoints
- In-memory H2 database for development
- MySQL support for production

## Features

- 🚀 **Spring Boot Framework** - Production-ready Java backend
- 🗄️ **Dual Database Support** - H2 (dev) and MySQL (prod)
- 📡 **RESTful API** - Clean, standardized endpoints
- 💾 **JPA/Hibernate** - Object-relational mapping
- 🔄 **Spring Data REST** - Automatic CRUD operations
- 🧪 **Unit Testing** - JUnit test coverage
- 📦 **Easy Deployment** - Executable JAR file
- 🔌 **JDBC Support** - Direct database access
- 🌐 **CORS Enabled** - iOS app integration

## Technologies Used

### Backend Framework
- **Spring Boot 3.2.5** - Application framework
- **Spring Data JPA** - Data persistence
- **Spring Data REST** - REST repository exposure
- **Spring Web** - REST controller support
- **Spring Data JDBC** - JDBC data access

### Database
- **H2 Database** - Embedded in-memory database (development)
- **MySQL** - Production database support

### Build & Runtime
- **Java 17** - Programming language
- **Gradle** - Build automation
- **JUnit** - Testing framework

### Additional
- **Jackson** - JSON serialization/deserialization

## API Endpoints

### Base URL
```
http://localhost:8080
```

### Available Endpoints

#### 1. Get Food Categories
**Endpoint**: `GET /foodCategoriesList`

**Description**: Returns a list of all food categories.

**Response**:
```json
[
  {
    "id": "1",
    "name": "Appetizers"
  },
  {
    "id": "2",
    "name": "Main Course"
  }
]
```

#### 2. Get Food Items by Category
**Endpoint**: `GET /foodItems?id={categoryId}`

**Parameters**:
- `id` (required): Category ID

**Description**: Returns all food items in a specific category.

**Response**:
```json
[
  {
    "id": "101",
    "name": "Bruschetta",
    "categoryId": "1",
    "description": "Toasted bread with toppings",
    "summary": "Classic Italian appetizer"
  }
]
```

#### 3. Get Recipe by Food Item ID
**Endpoint**: `GET /foodRecipe?id={foodItemId}`

**Parameters**:
- `id` (required): Food item ID

**Description**: Returns detailed recipe information for a specific food item.

**Response**:
```json
{
  "id": "1001",
  "foodItemId": "101",
  "ingredients": "Bread, tomatoes, garlic, basil, olive oil",
  "instructions": "1. Toast bread...",
  "prepTime": "15 minutes",
  "cookTime": "5 minutes"
}
```

## Project Structure

```
LearnSwiftBackend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/zchalmers/LearnSwiftBackend/
│   │   │       ├── Application.java              # Main application class
│   │   │       ├── Controller/
│   │   │       │   └── MainController.java       # REST endpoints
│   │   │       ├── Service/
│   │   │       │   ├── IMainService.java         # Service interface
│   │   │       │   └── MainService.java          # Business logic
│   │   │       └── Repositories/
│   │   │           ├── Model/
│   │   │           │   ├── FoodCategoryRecord.java
│   │   │           │   ├── FoodItemRecord.java
│   │   │           │   └── FoodRecipeRecord.java
│   │   │           ├── FoodCategoryRepo.java
│   │   │           ├── FoodItemRepo.java
│   │   │           └── FoodRecipeRepo.java
│   │   └── resources/
│   │       ├── application.properties            # Configuration
│   │       ├── data.sql                         # Sample data (optional)
│   │       └── schema.sql                       # Database schema (optional)
│   └── test/
│       └── java/
│           └── com/zchalmers/LearnSwiftBackend/
│               └── ApplicationTests.java         # Unit tests
├── build.gradle                                  # Gradle build configuration
├── settings.gradle                               # Gradle settings
└── README.md
```

## Installation & Setup

### Prerequisites

1. **Java Development Kit (JDK) 17** or higher
   - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
   - Verify installation: `java -version`

2. **Gradle** (optional, uses Gradle wrapper)
   - Gradle wrapper included in project
   - Or install manually: [Gradle Installation](https://gradle.org/install/)

3. **MySQL** (optional, for production)
   - Download from [MySQL Downloads](https://dev.mysql.com/downloads/)
   - Or use H2 in-memory database

### Setup Instructions

#### Option 1: Run Pre-Built JAR (Recommended for Quick Start)

1. **Download the JAR File**
   - Download from: [LearnSwiftH2Backend.jar](https://drive.google.com/file/d/1Y9d93OolDy_R7sTTfSC-Ae1Lypqngm7I/view?usp=sharing)

2. **Run the Application**
   ```bash
   java -jar LearnSwiftH2Backend.jar
   ```

3. **Access the API**
   - Open browser to `http://localhost:8080/foodCategoriesList`

#### Option 2: Build from Source

1. **Clone the Repository**
   ```bash
   git clone https://github.com/zchalmers/LearnSwiftBackend.git
   cd LearnSwiftBackend
   ```

2. **Build with Gradle**
   ```bash
   # Windows
   gradlew.bat build

   # macOS/Linux
   ./gradlew build
   ```

3. **Run the Application**
   ```bash
   # Using Gradle
   ./gradlew bootRun

   # Or run the built JAR
   java -jar build/libs/LearnSwiftBackend-0.0.1-SNAPSHOT.jar
   ```

4. **Verify It's Running**
   ```bash
   curl http://localhost:8080/foodCategoriesList
   ```

## Configuration

### Database Configuration

Edit `src/main/resources/application.properties`:

#### H2 Database (Default - In-Memory)
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

Access H2 console: `http://localhost:8080/h2-console`

#### MySQL Database (Production)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/learnswift
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```

### Server Configuration

```properties
# Change server port (default: 8080)
server.port=8080

# Enable CORS for iOS app
# Add to WebConfig if needed
```

### Logging

```properties
# Log level
logging.level.com.zchalmers.LearnSwiftBackend=DEBUG

# Log file
logging.file.name=app.log
```

## Development

### Running in Development Mode

```bash
# With Gradle
./gradlew bootRun

# With live reload (Spring DevTools)
./gradlew bootRun --continuous
```

### Running Tests

```bash
# Run all tests
./gradlew test

# Run with coverage
./gradlew test jacocoTestReport
```

### Building for Production

```bash
# Create executable JAR
./gradlew bootJar

# Output location
# build/libs/LearnSwiftBackend-0.0.1-SNAPSHOT.jar
```

## Database Schema

### FoodCategory Table
```sql
CREATE TABLE food_category (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
```

### FoodItem Table
```sql
CREATE TABLE food_item (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category_id VARCHAR(50),
    description TEXT,
    summary TEXT,
    FOREIGN KEY (category_id) REFERENCES food_category(id)
);
```

### FoodRecipe Table
```sql
CREATE TABLE food_recipe (
    id VARCHAR(50) PRIMARY KEY,
    food_item_id VARCHAR(50),
    ingredients TEXT,
    instructions TEXT,
    prep_time VARCHAR(50),
    cook_time VARCHAR(50),
    FOREIGN KEY (food_item_id) REFERENCES food_item(id)
);
```

## Testing the API

### Using cURL

```bash
# Get all categories
curl http://localhost:8080/foodCategoriesList

# Get food items by category ID
curl "http://localhost:8080/foodItems?id=1"

# Get recipe by food item ID
curl "http://localhost:8080/foodRecipe?id=101"
```

### Using Postman

1. Import the following as requests:
   - `GET http://localhost:8080/foodCategoriesList`
   - `GET http://localhost:8080/foodItems?id=1`
   - `GET http://localhost:8080/foodRecipe?id=101`

### Using Browser

Simply navigate to:
- `http://localhost:8080/foodCategoriesList`

## Deployment

### Deploy to Heroku

```bash
# Login to Heroku
heroku login

# Create app
heroku create learnswift-backend

# Add MySQL addon
heroku addons:create cleardb:ignite

# Deploy
git push heroku main

# View logs
heroku logs --tail
```

### Deploy to AWS

1. **Build JAR**: `./gradlew bootJar`
2. **Upload to EC2** or **Elastic Beanstalk**
3. **Configure environment variables**
4. **Run**: `java -jar LearnSwiftBackend-0.0.1-SNAPSHOT.jar`

### Docker Deployment

Create `Dockerfile`:
```dockerfile
FROM openjdk:17-jdk-slim
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

Build and run:
```bash
docker build -t learnswift-backend .
docker run -p 8080:8080 learnswift-backend
```

## Troubleshooting

### Common Issues

**"Port 8080 already in use"**
- Change port in `application.properties`: `server.port=8081`
- Or kill process using port 8080

**"Database connection failed"**
- Verify MySQL is running
- Check credentials in `application.properties`
- Ensure database exists: `CREATE DATABASE learnswift;`

**"Build failed"**
- Ensure Java 17 is installed: `java -version`
- Clean build: `./gradlew clean build`
- Check internet connection for dependency downloads

**"No such method error"**
- Verify Java version is 17+
- Rebuild project: `./gradlew clean build`

**"Cannot access H2 console"**
- Ensure `spring.h2.console.enabled=true` in properties
- Navigate to `http://localhost:8080/h2-console`

## Integration with iOS App

The iOS app [LearnSwift](https://github.com/zchalmers/LearnSwift) consumes this API:

1. **Configure Base URL** in iOS app (NetworkManager or similar)
   ```swift
   let baseURL = "http://localhost:8080"  // Development
   // let baseURL = "https://your-domain.com"  // Production
   ```

2. **Make API Calls** from iOS
   ```swift
   // Fetch categories
   let url = URL(string: "\(baseURL)/foodCategoriesList")!
   URLSession.shared.dataTask(with: url) { data, response, error in
       // Handle response
   }.resume()
   ```

## Future Enhancements

- [ ] Add authentication (JWT, OAuth2)
- [ ] Implement pagination for large datasets
- [ ] Add search functionality
- [ ] Support image uploads for food items
- [ ] Add user favorites/ratings
- [ ] Implement caching (Redis)
- [ ] Add API versioning
- [ ] Create admin panel
- [ ] Add GraphQL endpoint
- [ ] Implement WebSocket for real-time updates

## Contributing

Contributions are welcome! Please:
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Write/update tests
5. Submit a pull request

## Requirements

- **Java**: 17 or higher
- **Spring Boot**: 3.2.5
- **Database**: H2 (development) or MySQL (production)
- **Build Tool**: Gradle 8.x

## License

This project is available for educational purposes.

## Contact

- **GitHub**: [@zchalmers](https://github.com/zchalmers)

## Related Project

iOS Frontend: [LearnSwift](https://github.com/zchalmers/LearnSwift)

## Acknowledgments

- Built with Spring Boot and Spring Data
- H2 Database for easy development
- Jackson for JSON processing
