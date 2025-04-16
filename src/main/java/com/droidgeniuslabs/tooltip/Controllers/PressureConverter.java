package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;

public class PressureConverter {
    @FXML private TextField pressureInput;
    @FXML private ChoiceBox<String> pressureFrom;
    @FXML private ChoiceBox<String> pressureTo;
    @FXML private Label pressureResult;
    private final Utilities utilities = new Utilities();
    private final List<String> units = Arrays.asList("Pa", "Bar", "Atm", "Psi");
    @FXML
    public void initialize() {
        pressureFrom.getItems().addAll(units);
        pressureTo.getItems().addAll(units);
        pressureFrom.setValue("Pa");
        pressureTo.setValue("Bar");
    }
    @FXML
    public void convertPressure() {
        try {
            double input = Double.parseDouble(pressureInput.getText());
            String from = pressureFrom.getValue();
            String to = pressureTo.getValue();
            double inputPa = utilities.convertToPa(input, from);
            double result = utilities.convertFromPa(inputPa, to);
            pressureResult.setText(String.format("%.4f %s", result, to));
        } catch (NumberFormatException e) {
            pressureResult.setText("Μη έγκυρη τιμή!");
        }
    }
}
