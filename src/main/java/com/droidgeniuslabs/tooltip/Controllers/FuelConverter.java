package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Arrays;
import java.util.List;

public class FuelConverter {
    public final List<String> units = Arrays.asList("L/100km", "MPG (UK)", "MPG (US)");
    @FXML private TextField inputField;
    @FXML private ComboBox<String> fromUnit;
    @FXML private ComboBox<String> toUnit;
    @FXML private Label resultLabel;
    private final Utilities utilities = new Utilities();
    public void initialize()
    {
	fromUnit.getItems().addAll(units);
	toUnit.getItems().addAll(units);
	fromUnit.setValue("L/100km");
	toUnit.setValue("MPG (US)");
    }
    public void onConvert() {
	try {
	    double value = Double.parseDouble(inputField.getText());
	    String from = fromUnit.getValue();
	    String to = toUnit.getValue();

	    double result = utilities.convertFuel(value, from, to);
	    resultLabel.setText(String.format("Result: %.2f %s", result, to));

	} catch (Exception e) {
	    resultLabel.setText("Invalid input.");
	}
    }
}
