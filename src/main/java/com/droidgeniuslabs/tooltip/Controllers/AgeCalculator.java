package com.droidgeniuslabs.tooltip.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AgeCalculator {
    @FXML private TextField birthDateInput;
    @FXML private Label resultLabel;

    @FXML
    private void onCalculateAge() {
	try {
	    String birthDateString = birthDateInput.getText();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate birthDate = LocalDate.parse(birthDateString, formatter);

	    LocalDate currentDate = LocalDate.now();
	    Period period = Period.between(birthDate, currentDate);
	    resultLabel.setText(String.format("Ηλικία: %d χρόνια, %d μήνες, %d ημέρες",
			    period.getYears(), period.getMonths(), period.getDays()));
	} catch (Exception e) {
	    resultLabel.setText("Εισάγετε έγκυρη ημερομηνία.");
	}
    }
}
