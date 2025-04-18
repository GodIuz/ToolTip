package com.droidgeniuslabs.tooltip.Model;

import java.util.List;

public class Question {
    private final String questionText;
    private final List<String> incorrectAnswers;
    private final String correctAnswer;

    public Question(String questionText, List<String> options, List<String> incorrectAnswers, String correctAnswer) {
	this.questionText = questionText;
	this.incorrectAnswers = incorrectAnswers;
	this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }
    public List<String> getIncorrectAnswers() { return incorrectAnswers; }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
