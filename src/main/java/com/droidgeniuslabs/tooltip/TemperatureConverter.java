package com.droidgeniuslabs.tooltip;

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

            double celsius = toCelsius(inputTemp, from);
            double converted = fromCelsius(celsius, to);

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

    private double toCelsius(double temp, String from) {
        return switch (from) {
            case "Celsius" -> temp;
            case "Fahrenheit" -> (temp - 32) * 5 / 9;
            case "Kelvin" -> temp - 273.15;
            case "Rankine" -> (temp - 491.67) * 5 / 9;
            case "Reaumur" -> temp * 1.25;
            default -> throw new IllegalArgumentException("Unknown unit: " + from);
        };
    }

    private double fromCelsius(double temp, String to) {
        return switch (to) {
            case "Celsius" -> temp;
            case "Fahrenheit" -> (temp * 9 / 5) + 32;
            case "Kelvin" -> temp + 273.15;
            case "Rankine" -> (temp + 273.15) * 9 / 5;
            case "Reaumur" -> temp * 0.8;
            default -> throw new IllegalArgumentException("Unknown unit: " + to);
        };
    }
}
