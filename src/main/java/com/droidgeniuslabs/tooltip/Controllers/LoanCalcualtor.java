package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoanCalcualtor {
    @FXML private TextField principalInput;
    @FXML private TextField rateInput;
    @FXML private TextField timeInput;
    @FXML private Label resultLabel;
    public Utilities utilities = new Utilities();

    public void onCalculateLoan() {
	try {
	    double principal = Double.parseDouble(principalInput.getText());
	    double rate = Double.parseDouble(rateInput.getText()) / 100;
	    double time = Double.parseDouble(timeInput.getText());
	    double totalAmount = utilities.calculateTotalAmount(principal,rate,time);
	    double monthlyPayment = utilities.calculateMonthlyPayment(totalAmount,time);
	    resultLabel.setText(String.format("Total Amount: %.2f, Monthly Payment: %.2f", totalAmount, monthlyPayment));
	} catch (Exception e) {
	    resultLabel.setText("Invalid input.");
	}
    }


}
