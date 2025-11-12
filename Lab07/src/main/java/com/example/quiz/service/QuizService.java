package com.example.quiz.service;

import com.example.quiz.model.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class QuizService {
    private Quiz quiz;

    public QuizService() {
        // Initialize quiz with sample questions
        List<Question> questions = Arrays.asList(
            new Question(1, "What is the capital of France?", 
                Arrays.asList("London", "Berlin", "Paris", "Madrid"), 2),
            new Question(2, "What is 2 + 2?", 
                Arrays.asList("3", "4", "5", "6"), 1),
            new Question(3, "Which planet is closest to the Sun?", 
                Arrays.asList("Venus", "Mercury", "Earth", "Mars"), 1),
            new Question(4, "What is the largest ocean?", 
                Arrays.asList("Atlantic", "Indian", "Arctic", "Pacific"), 3),
            new Question(5, "Who wrote Romeo and Juliet?", 
                Arrays.asList("Dickens", "Shakespeare", "Austen", "Hemingway"), 1)
        );
        this.quiz = new Quiz("General Knowledge Quiz", questions);
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void submitAnswer(int questionId, int answer) {
        quiz.submitAnswer(questionId, answer);
    }

    public void nextQuestion() {
        quiz.nextQuestion();
    }

    public void resetQuiz() {
        quiz.reset();
    }
}