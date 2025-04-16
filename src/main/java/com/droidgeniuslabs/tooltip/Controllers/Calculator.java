package com.droidgeniuslabs.tooltip.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Calculator {
    @FXML
    private TextField inputField;

    @FXML
    private Text resultText;

    @FXML
    private void handleAddition() {
	calculate("+");
    }

    @FXML
    private void handleSubtraction() {
	calculate("-");
    }

    @FXML
    private void handleMultiplication() {
	calculate("*");
    }

    @FXML
    private void handleDivision() {
	calculate("/");
    }

    @FXML
    private void handleModulus() {
	calculate("%");
    }

    @FXML
    private void handleLog() {
	calculate("log");
    }

    @FXML
    private void handleLn() {
	calculate("ln");
    }

    @FXML
    private void handlePower() {
	calculate("power");
    }

    @FXML
    private void handleFactorial() {
	calculate("factorial");
    }

    @FXML
    private void handleSqrt() {
	calculate("sqrt");
    }

    @FXML
    private void handleSin() {
	calculateTrig("sin");
    }

    @FXML
    private void handleCos() {
	calculateTrig("cos");
    }

    @FXML
    private void handleTan() {
	calculateTrig("tan");
    }

    private void calculate(String operation) {
	try {
	    double input = Double.parseDouble(inputField.getText());
	    double result = 0;

	    switch (operation) {
	    case "+":
		result = input + input;
		break;
	    case "-":
		result = input - input;
		break;
	    case "*":
		result = input * input;
		break;
	    case "/":
		result = input / input;
		break;
	    case "%":
		result = input % input;
		break;
	    case "log":
		result = Math.log10(input);
		break;
	    case "ln":
		result = Math.log(input);
		break;
	    case "power":
		result = Math.pow(input, 2); // Προσαρμογή δύναμης
		break;
	    case "factorial":
		result = factorial(input);
		break;
	    case "sqrt":
		result = Math.sqrt(input);
		break;
	    default:
		resultText.setText("Άγνωστη πράξη!");
		return;
	    }
	    resultText.setText("Αποτέλεσμα: " + result);
	} catch (Exception e) {
	    utilities.showError("Σφάλμα στην εισαγωγή!");
	}
    }

    private void calculateTrig(String function) {
	try {
	    double input = Double.parseDouble(inputField.getText());
	    double result = 0;

	    switch (function) {
	    case "sin":
		result = Math.sin(Math.toRadians(input));
		break;
	    case "cos":
		result = Math.cos(Math.toRadians(input));
		break;
	    case "tan":
		result = Math.tan(Math.toRadians(input));
		break;
	    default:
		resultText.setText("Άγνωστη τριγωνομετρική συνάρτηση!");
		return;
	    }

	    resultText.setText(function + " : " + result);
	} catch (Exception e) {
	    showError("Σφάλμα στην εισαγωγή!");
	}
    }
}
