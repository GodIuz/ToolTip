package com.droidgeniuslabs.tooltip.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TextBinaryConverter {
    @FXML private TextField textInput;
    @FXML private TextField binaryInput;
    @FXML private Label resultLabel;

    public void onConvertToBinary() {
	String text = textInput.getText();
	StringBuilder binary = new StringBuilder();
	for (char c : text.toCharArray()) {
	    binary.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0')).append(" ");
	}
	resultLabel.setText("Binary: " + binary.toString());
    }

    public void onConvertToText() {
	String binary = binaryInput.getText();
	String[] binaryValues = binary.split(" ");
	StringBuilder text = new StringBuilder();
	for (String b : binaryValues) {
	    if (b.length() == 8) {
		text.append((char) Integer.parseInt(b, 2));
	    }
	}
	resultLabel.setText("Text: " + text.toString());
    }
}
