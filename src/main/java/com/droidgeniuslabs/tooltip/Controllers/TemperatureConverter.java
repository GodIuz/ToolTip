package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class TemperatureConverter {
    public ComboBox fromUnit;
    public ComboBox toUnit;
    public Label resultLabel;
    public TextField temperatureField;
    private static final List<String> units = Arrays.asList("Celsius", "Fahrenheit", "Kelvin", "Rankine", "Reaumur");

    @FXML
    public void initialize() {
        fromUnit.getItems().addAll(units);
        toUnit.getItems().addAll(units);
        fromUnit.setValue("Celsius");
        toUnit.setValue("Fahrenheit");
    }

    @FXML
    public void convertTemperature() {
        try {
            double inputTemp = Double.parseDouble(temperatureField.getText());
            String from = fromUnit.getValue().toString();
            String to =  toUnit.getValue().toString();
            Utilities utilities = new Utilities();
            double celsius = utilities.toCelsius(inputTemp, from);
            double converted = utilities.fromCelsius(celsius, to);

            resultLabel.setText(String.format("%.2f %s = %.2f %s", inputTemp, from, converted, to));
            resultLabel.setTextFill(Color.DARKGREEN);

        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid number.");
            resultLabel.setTextFill(Color.RED);
        } catch (Exception e) {
            resultLabel.setText("Conversion error.");
            resultLabel.setTextFill(Color.RED);
        }
    }
}
