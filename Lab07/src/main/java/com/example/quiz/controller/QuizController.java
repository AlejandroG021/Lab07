package com.example.quiz.controller;

import com.example.quiz.model.*;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/")
    public String index(Model model) {
        Quiz quiz = quizService.getQuiz();
        model.addAttribute("quizTitle", quiz.getTitle());
        return "index";
    }

    @PostMapping("/start")
    public String startQuiz() {
        quizService.resetQuiz();
        return "redirect:/quiz";
    }

    @GetMapping("/quiz")
    public String showQuiz(Model model) {
        Quiz quiz = quizService.getQuiz();
        
        // Check if quiz needs to be reset (e.g., if completed)
        if (quiz.isCompleted()) {
            quizService.resetQuiz();
        }
        
        Question currentQuestion = quiz.getCurrentQuestion();
        
        if (currentQuestion == null) {
            quizService.resetQuiz();
            currentQuestion = quiz.getCurrentQuestion();
        }
        
        model.addAttribute("question", currentQuestion);
        model.addAttribute("questionNumber", quiz.getCurrentQuestionIndex() + 1);
        model.addAttribute("totalQuestions", quiz.getTotalQuestions());
        
        return "quiz";
    }

    @PostMapping("/submit")
    public String submitAnswer(@RequestParam int questionId,
                              @RequestParam int answer) {
        Quiz quiz = quizService.getQuiz();
        quizService.submitAnswer(questionId, answer);
        
        if (quiz.getCurrentQuestionIndex() < quiz.getTotalQuestions() - 1) {
            quizService.nextQuestion();
            return "redirect:/quiz";
        } else {
            return "redirect:/results";
        }
    }

    @GetMapping("/results")
    public String showResults(Model model) {
        Quiz quiz = quizService.getQuiz();
        
        int score = quiz.calculateScore();
        int total = quiz.getTotalQuestions();
        
        model.addAttribute("score", score);
        model.addAttribute("total", total);
        model.addAttribute("percentage", (score * 100.0 / total));
        
        return "results";
    }
}