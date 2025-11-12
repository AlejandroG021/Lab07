package com.example.quiz.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz {
    private String title;
    private List<Question> questions;
    private int currentQuestionIndex;
    private Map<Integer, Integer> answers;

    public Quiz(String title, List<Question> questions) {
        this.title = title;
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.answers = new HashMap<>();
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    public void submitAnswer(int questionId, int answer) {
        answers.put(questionId, answer);
    }

    public void nextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
        }
    }

    public boolean isCompleted() {
        return currentQuestionIndex >= questions.size() - 1 && 
               answers.size() == questions.size();
    }

    public int calculateScore() {
        int score = 0;
        for (Question q : questions) {
            Integer answer = answers.get(q.getId());
            if (answer != null && q.isCorrect(answer)) {
                score++;
            }
        }
        return score;
    }

    public void reset() {
        currentQuestionIndex = 0;
        answers.clear();
    }

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }
    public int getCurrentQuestionIndex() { return currentQuestionIndex; }
    public Map<Integer, Integer> getAnswers() { return answers; }
}