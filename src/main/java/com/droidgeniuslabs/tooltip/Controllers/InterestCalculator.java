package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InterestCalculator {
    @FXML private TextField principalField, rateField, timeField, compoundFrequencyField;
    @FXML private Label simpleInterestResult, compoundInterestResult;
    private final Utilities utilities = new Utilities();

    public void calculateSimpleInterest() {
	try {
	    double P = Double.parseDouble(principalField.getText());
	    double R = Double.parseDouble(rateField.getText());
	    double T = Double.parseDouble(timeField.getText());
	    double interest = utilities.calculateSimpleInterest(P, R, T);
	    simpleInterestResult.setText("Απλός Τόκος: €" + String.format("%.2f", interest));
	} catch (Exception e) {
	    simpleInterestResult.setText("Σφάλμα στα δεδομένα.");
	}
    }

    public void calculateCompoundInterest() {
	double P = Double.parseDouble(principalField.getText());
	double R = Double.parseDouble(rateField.getText()) / 100.0;
	double T = Double.parseDouble(timeField.getText());
	int n = Integer.parseInt(compoundFrequencyField.getText());
	double compoundInterest = utilities.calculateCompoundInterest(P, R, T, n);
	compoundInterestResult.setText("Σύνθετος Τόκος: €" + String.format("%.2f", compoundInterest));
    }
}
