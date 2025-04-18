package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Model.Question;
import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.*;

public class TriviaGame implements Initializable {
    @FXML private Label questionLabel;
    @FXML private VBox answersBox;
    @FXML private Label scoreLabel;
    private List<Question> questions;
    private final int currentQuestion = 0;
    private final int score = 0;
    private final Utilities utilities = new Utilities();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        utilities.loadQuestions();
        if (!questions.isEmpty()) {
            showQuestion(questions.getFirst());
        }
        if (questions == null || questions.isEmpty()) {
            utilities.showAlert("Failed to load the queasstions");
        }
    }

    public void showQuestion(Question q) {
        questionLabel.setText(q.getQuestionText());
        answersBox.getChildren().clear();

        List<String> answers = new ArrayList<>(q.getIncorrectAnswers());
        answers.add(q.getCorrectAnswer());
        Collections.shuffle(answers);

        for (String answer : answers) {
            Button button = new Button(answer);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setOnAction(e -> utilities.handleAnswer(q, answer));
            answersBox.getChildren().add(button);
        }
    }
    public void showLeaderboard() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Game Over! Your score: " + score);
        dialog.setContentText("Enter your name for the leaderboard:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> utilities.saveToLeaderboard(name, score));
    }
}
