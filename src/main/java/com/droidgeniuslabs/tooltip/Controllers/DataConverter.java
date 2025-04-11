package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
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
            Utilities utilities = new Utilities();
            double inputInBytes = utilities.convertToBytes(inputValue, inputUnit);
            double result = utilities.convertFromBytes(inputInBytes, outputUnit);
            outputField.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            outputField.setText("Invalid input");
        }
    }

}

