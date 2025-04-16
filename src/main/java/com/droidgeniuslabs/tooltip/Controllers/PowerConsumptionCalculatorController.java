package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PowerConsumptionCalculatorController {
    @FXML private TextField powerInput;
    @FXML private TextField hoursInput;
    @FXML private TextField rateInput;
    @FXML private Label resultLabel;
    private final Utilities utilities = new Utilities();

    @FXML
    private void onCalculatePowerCost() {
	try {
	    double power = Double.parseDouble(powerInput.getText());
	    double hours = Double.parseDouble(hoursInput.getText());
	    double rate = Double.parseDouble(rateInput.getText());

	    double cost = utilities.calculatePowerCost(power,hours,rate);

	    resultLabel.setText(String.format("Cost of usage: %.2f €", cost));
	} catch (Exception e) {
	    resultLabel.setText("Invalid input");
	}
    }
}
