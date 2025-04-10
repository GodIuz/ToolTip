package com.droidgeniuslabs.tooltip;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;

public class DataConverter {
    public TextField inputField;
    private static final List<String> units = Arrays.asList("Byte", "Kilobytes", "Megabytes", "Gigabytes", "Terabytes", "Petabytes");
    public ComboBox inputUnitComboBox;
    public ComboBox outputUnitComboBox;
    public TextField outputField;

    @FXML
    public void initialize() {
        inputUnitComboBox.getItems().addAll(units);
        outputUnitComboBox.getItems().addAll(units);
        inputUnitComboBox.setValue("Bytes");
        outputUnitComboBox.setValue("Kilobytes");
    }
    @FXML
    public void handleConvertButton() {
        try {
            double inputValue = Double.parseDouble(inputField.getText().trim());
            String inputUnit = inputUnitComboBox.getValue().toString();
            String outputUnit = outputUnitComboBox.getValue().toString();
            double inputInBytes = convertToBytes(inputValue, inputUnit);
            double result = convertFromBytes(inputInBytes, outputUnit);
            outputField.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            outputField.setText("Invalid input");
        }
    }
    private double convertToBytes(double value, String unit) {
        switch (unit.toLowerCase()) {
            case "kilobytes":
                return value * 1024;
            case "megabytes":
                return value * 1024 * 1024;
            case "gigabytes":
                return value * 1024 * 1024 * 1024;
            case "terabytes":
                return value * 1024 * 1024 * 1024 * 1024;
            case "petabytes":
                return value * 1024 * 1024 * 1024 * 1024 * 1024;
            case "bytes":
            default:
                return value;
        }
    }
    private double convertFromBytes(double value, String unit) {
        switch (unit.toLowerCase()) {
            case "kilobytes":
                return value / 1024;
            case "megabytes":
                return value / (1024 * 1024);
            case "gigabytes":
                return value / (1024 * 1024 * 1024);
            case "terabytes":
                return value / (1024 * 1024 * 1024 * 1024);
            case "petabytes":
                return value / (1024 * 1024 * 1024 * 1024 * 1024);
            case "bytes":
            default:
                return value;
        }
    }
}
