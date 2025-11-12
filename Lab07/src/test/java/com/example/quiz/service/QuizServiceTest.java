package com.example.quiz.service;

import com.example.quiz.model.Quiz;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuizServiceTest {

    @Test
    public void testGetQuiz() {
        QuizService service = new QuizService();
        Quiz quiz = service.getQuiz();
        
        assertNotNull(quiz);
        assertNotNull(quiz.getTitle());
        assertTrue(quiz.getTotalQuestions() > 0);
    }

    @Test
    public void testSubmitAnswer() {
        QuizService service = new QuizService();
        Quiz quiz = service.getQuiz();
        
        service.submitAnswer(1, 2);
        assertEquals(1, quiz.getAnswers().size());
        assertEquals(2, quiz.getAnswers().get(1));
    }

    @Test
    public void testNextQuestion() {
        QuizService service = new QuizService();
        Quiz quiz = service.getQuiz();
        
        int initialIndex = quiz.getCurrentQuestionIndex();
        service.nextQuestion();
        
        assertEquals(initialIndex + 1, quiz.getCurrentQuestionIndex());
    }

    @Test
    public void testResetQuiz() {
        QuizService service = new QuizService();
        Quiz quiz = service.getQuiz();
        
        service.submitAnswer(1, 0);
        service.nextQuestion();
        service.submitAnswer(2, 1);
        
        service.resetQuiz();
        
        assertEquals(0, quiz.getCurrentQuestionIndex());
        assertEquals(0, quiz.getAnswers().size());
    }
}