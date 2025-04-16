package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Calculator {
    @FXML
    private TextField inputField;
	@FXML
	private TextField inputField2;
    @FXML
    private Text resultText;
	double result = 0;
	Utilities utilities = new Utilities();

    @FXML
    private void handleAddition() {
        double input = Double.parseDouble(inputField.getText());
        double input2 = Double.parseDouble(inputField2.getText());
		result  = utilities.calculate("+",input,input2);
		resultText.setText("Αποτέλεσμα: " + result);
    }
    @FXML
    private void handleSubtraction() {

        double input = Double.parseDouble(inputField.getText());
        double input2 = Double.parseDouble(inputField2.getText());
		result = utilities.calculate("-",input,input2);
		resultText.setText("Αποτέλεσμα: " + result);
    }
    @FXML
    private void handleMultiplication() {
        double input = Double.parseDouble(inputField.getText());
        double input2 = Double.parseDouble(inputField2.getText());
		result = utilities.calculate("*",input,input2);
		resultText.setText("Αποτέλεσμα: " + result);
    }
    @FXML
    private void handleDivision() {
        double input = Double.parseDouble(inputField.getText());
        double input2 = Double.parseDouble(inputField2.getText());
		result =  utilities.calculate("/",input,input2);
		resultText.setText("Αποτέλεσμα: " + result);
    }
    @FXML
    private void handleModulus() {
        double input = Double.parseDouble(inputField.getText());
        double input2 = Double.parseDouble(inputField2.getText());
		result = utilities.calculate("%",input,input2);
		resultText.setText("Αποτελεσμα : " + result);
    }
    @FXML
    private void handleLog() {
        double input = Double.parseDouble(inputField.getText());
		result = utilities.calcualteAdvnaced("log",input);
		resultText.setText("Αποτελεσμα : " + result);
    }
    @FXML
    private void handleLn() {
        double input = Double.parseDouble(inputField.getText());
		result = utilities.calcualteAdvnaced("ln",input);
		resultText.setText("Αποτελεσμα : " + result);
    }
    @FXML
    private void handlePower() {
        double input = Double.parseDouble(inputField.getText());
		result = utilities.calcualteAdvnaced("power",input);
		resultText.setText("Αποτελεσμα : " + result);
    }
    @FXML
    private void handleFactorial() {
        double input = Double.parseDouble(inputField.getText());
		result = utilities.calcualteAdvnaced("factorial",input);
		resultText.setText("Αποτελεσμα : " + result);
    }
    @FXML
    private void handleSqrt() {
        double input = Double.parseDouble(inputField.getText());
		result = utilities.calcualteAdvnaced("sqrt",input);
		resultText.setText("Αποτελεσμα : " + result);
    }
    @FXML
    private void handleSin() {
        double input = Double.parseDouble(inputField.getText());
		result = utilities.calculateTrig("sin",input);
		resultText.setText("Αποτελεσμα : " + result);
    }
    @FXML
    private void handleCos() {
        double input = Double.parseDouble(inputField.getText());
		result = utilities.calculateTrig("cos",input);
		resultText.setText("Αποτελεσμα : " + result);
    }
    @FXML
    private void handleTan() {
        double input = Double.parseDouble(inputField.getText());
		result = utilities.calculateTrig("tan",input);
		resultText.setText("Αποτελεσμα : " + result);
    }
}
