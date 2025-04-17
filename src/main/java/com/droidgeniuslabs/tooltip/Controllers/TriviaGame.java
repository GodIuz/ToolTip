package com.droidgeniuslabs.tooltip.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.*;


public class TriviaGame {
    @FXML private Label questionLabel;
    @FXML private RadioButton optionA, optionB, optionC, optionD;
    @FXML private Label resultLabel;
    @FXML
    private final ToggleGroup optionsGroup = new ToggleGroup();

    private int currentIndex = 0;
    private final List<Question> questions = List.of(
            new Question("What is the capital of France?", "Paris", List.of("Paris", "Rome", "Berlin", "Madrid")),
            new Question("2 + 2 = ?", "4", List.of("3", "4", "5", "6"))
    );

    @FXML
    public void initialize() {
        optionA.setToggleGroup(optionsGroup);
        optionB.setToggleGroup(optionsGroup);
        optionC.setToggleGroup(optionsGroup);
        optionD.setToggleGroup(optionsGroup);
        loadQuestion();
    }

    private void loadQuestion() {
        Question q = questions.get(currentIndex);
        questionLabel.setText(q.getText());
        List<String> opts = q.getOptions();
        optionA.setText(opts.get(0));
        optionB.setText(opts.get(1));
        optionC.setText(opts.get(2));
        optionD.setText(opts.get(3));
        optionsGroup.selectToggle(null);
        resultLabel.setText("");
    }

    @FXML
    public void nextQuestion() {
        RadioButton selected = (RadioButton) optionsGroup.getSelectedToggle();
        if (selected != null) {
            String answer = selected.getText();
            String correct = questions.get(currentIndex).getAnswer();
            if (answer.equals(correct)) {
                resultLabel.setText("Σωστό!");
            } else {
                resultLabel.setText("Λάθος! Η σωστή απάντηση ήταν: " + correct);
            }
        }
        currentIndex = (currentIndex + 1) % questions.size();
        loadQuestion();
    }

    static class Question {
        private final String text, answer;
        private final List<String> options;
        public Question(String text, String answer, List<String> options) {
            this.text = text; this.answer = answer; this.options = options;
        }
        public String getText() { return text; }
        public String getAnswer() { return answer; }
        public List<String> getOptions() { return options; }
    }
}
