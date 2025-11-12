package com.example.quiz.model;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {

    @Test
    public void testQuestionIsCorrect() {
        Question q = new Question(1, "Test?", Arrays.asList("A", "B"), 0);
        assertTrue(q.isCorrect(0));
        assertFalse(q.isCorrect(1));
    }

    @Test
    public void testQuizGetTotalQuestions() {
        Quiz quiz = new Quiz("Test", Arrays.asList(
            new Question(1, "Q1", Arrays.asList("A", "B"), 0),
            new Question(2, "Q2", Arrays.asList("A", "B"), 1)
        ));
        assertEquals(2, quiz.getTotalQuestions());
    }

    @Test
    public void testQuizGetCurrentQuestion() {
        Question q1 = new Question(1, "Q1", Arrays.asList("A", "B"), 0);
        Question q2 = new Question(2, "Q2", Arrays.asList("A", "B"), 1);
        Quiz quiz = new Quiz("Test", Arrays.asList(q1, q2));
        
        assertEquals(q1, quiz.getCurrentQuestion());
        quiz.nextQuestion();
        assertEquals(q2, quiz.getCurrentQuestion());
    }

    @Test
    public void testQuizCalculateScore() {
        Quiz quiz = new Quiz("Test", Arrays.asList(
            new Question(1, "Q1", Arrays.asList("A", "B"), 0),
            new Question(2, "Q2", Arrays.asList("A", "B"), 1)
        ));
        
        quiz.submitAnswer(1, 0);  // correct
        quiz.submitAnswer(2, 0);  // incorrect
        
        assertEquals(1, quiz.calculateScore());
    }

    @Test
    public void testQuizNavigation() {
        Quiz quiz = new Quiz("Test", Arrays.asList(
            new Question(1, "Q1", Arrays.asList("A", "B"), 0),
            new Question(2, "Q2", Arrays.asList("A", "B"), 1)
        ));
        
        assertEquals(0, quiz.getCurrentQuestionIndex());
        assertFalse(quiz.isCompleted());
        
        quiz.submitAnswer(1, 0);
        quiz.nextQuestion();
        assertEquals(1, quiz.getCurrentQuestionIndex());
        
        quiz.submitAnswer(2, 1);
        assertTrue(quiz.isCompleted());
    }

    @Test
    public void testQuizReset() {
        Quiz quiz = new Quiz("Test", Arrays.asList(
            new Question(1, "Q1", Arrays.asList("A", "B"), 0),
            new Question(2, "Q2", Arrays.asList("A", "B"), 1)
        ));
        
        quiz.submitAnswer(1, 0);
        quiz.nextQuestion();
        quiz.submitAnswer(2, 1);
        
        quiz.reset();
        
        assertEquals(0, quiz.getCurrentQuestionIndex());
        assertEquals(0, quiz.getAnswers().size());
        assertFalse(quiz.isCompleted());
    }
}