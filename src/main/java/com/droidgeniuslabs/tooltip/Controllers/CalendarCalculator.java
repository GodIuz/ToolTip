package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.scene.control.DatePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalendarCalculator {
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label resultLabel;
    Utilities utilities = new Utilities();
    public void onCalculateDate() {
	LocalDate date = datePicker.getValue();

	if (date == null) {
	    resultLabel.setText("⚠️ Παρακαλώ επέλεξε ημερομηνία.");
	    return;
	}
	DayOfWeek dayOfWeek = date.getDayOfWeek();
	String dayNameGreek = utilities.getGreekDayName(dayOfWeek);
	String formattedDate = String.format("%s, %d %s %d",
			dayNameGreek,
			date.getDayOfMonth(),
			utilities.getGreekMonthName(date.getMonthValue()),
			date.getYear());

	resultLabel.setText("📅 " + formattedDate);
    }
}
