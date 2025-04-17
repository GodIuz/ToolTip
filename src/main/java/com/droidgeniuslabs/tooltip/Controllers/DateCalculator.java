package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateCalculator {
    @FXML private DatePicker startDatePicker, endDatePicker, baseDatePicker;
    @FXML private Label daysBetweenLabel, resultDateLabel;
    @FXML private TextField daysInputField;
    private final Utilities utilities = new Utilities();

    public void calculateDateDifference() {
	LocalDate start = startDatePicker.getValue();
	LocalDate end = endDatePicker.getValue();
	if (start != null && end != null) {
	    long daysBetween = ChronoUnit.DAYS.between(start, end);
	    daysBetweenLabel.setText("Μέρες διαφοράς: " + daysBetween);
	}
    }

    public void addDays() {
	utilities.handleAddSubtract(true);
    }

    public void subtractDays() {
	utilities.handleAddSubtract(false);
    }
}
