package com.droidgeniuslabs.tooltip.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class UnixTimestampConverterController {
    @FXML private TextField timestampInput;
    @FXML private TextField dateInput;
    @FXML private Label resultLabel;

    public void onConvertToDate() {
	try {
	    long timestamp = Long.parseLong(timestampInput.getText());
	    LocalDateTime dateTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC);
	    resultLabel.setText("Converted Date: " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	} catch (Exception e) {
	    resultLabel.setText("Invalid Unix Timestamp.");
	}
    }

    public void onConvertToUnix() {
	try {
	    String dateText = dateInput.getText();
	    LocalDateTime dateTime = LocalDateTime.parse(dateText, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	    long timestamp = dateTime.toEpochSecond(ZoneOffset.UTC);
	    resultLabel.setText("Converted Timestamp: " + timestamp);
	} catch (Exception e) {
	    resultLabel.setText("Invalid Date.");
	}
    }
}
