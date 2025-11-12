# Spring Boot Thymeleaf Quiz Server

A web-based quiz application built with Spring Boot and Thymeleaf for CSE2102 Lab 7.

## Project Description

This is a simple quiz server that allows users to take a multiple-choice quiz through a web interface. The application presents questions one at a time, tracks user answers, and displays the final score upon completion.

## Project Structure

```
quiz-server/
├── src/
│   ├── main/
│   │   ├── java/com/example/quiz/
│   │   │   ├── QuizApplication.java          # Main Spring Boot application
│   │   │   ├── model/
│   │   │   │   ├── Quiz.java                 # Quiz model with state management
│   │   │   │   └── Question.java             # Question model
│   │   │   ├── controller/
│   │   │   │   └── QuizController.java       # MVC controller for handling requests
│   │   │   └── service/
│   │   │       └── QuizService.java          # Business logic service
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── index.html                # Landing page
│   │       │   ├── quiz.html                 # Quiz question page
│   │       │   └── results.html              # Results page
│   │       └── application.properties        # Configuration
│   └── test/
│       └── java/com/example/quiz/
│           ├── model/
│           │   └── QuizTest.java             # Model unit tests
│           └── service/
│               └── QuizServiceTest.java      # Service unit tests
├── pom.xml                                    # Maven configuration
└── README.md                                  # This file
```

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

To check your Java version:
```bash
java -version
```

To check your Maven version:
```bash
mvn -version
```

## Installation & Setup

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the tests**
   ```bash
   mvn test
   ```

## Running the Application

### Start the server:
```bash
mvn spring-boot:run
```

The application will start on port 8080.

### Access the application:
Open your web browser and navigate to:
```
http://localhost:8080
```

For GitHub Codespaces or similar environments, use the provided forwarded URL.

## Using the Application

1. **Start Page**: Click the "Start Quiz" button to begin
2. **Quiz Page**: 
   - Read each question carefully
   - Select your answer from the multiple-choice options
   - Click "Submit Answer" to proceed to the next question
   - Progress is shown at the top (e.g., "Question 2 of 5")
3. **Results Page**: 
   - View your score and percentage
   - Click "Take Quiz Again" to restart

## Testing

The project includes comprehensive unit tests for the model and service layers.

### Run tests:
```bash
mvn test
```

## Configuration

### Customizing Quiz Questions
Edit `QuizService.java` constructor to add/modify questions:

```java
List<Question> questions = Arrays.asList(
    new Question(1, "Your question?", 
        Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4"), 
        correctAnswerIndex),
    // Add more questions...
);
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Landing page |
| POST | `/start` | Start/restart the quiz |
| GET | `/quiz` | Display current question |
| POST | `/submit` | Submit answer and move to next question |
| GET | `/results` | Display final results |
