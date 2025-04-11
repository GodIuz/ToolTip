package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Arrays;
import java.util.List;

public class TimeConverter {
   private static final List<String> units = Arrays.asList("Picoseconds (ps)", "Microsecond (μs)", "Millisecond (ms)", "Second (s)", "Minute (m)","Hour (h)", "Day (d)", "Week (wk)", "Year (y)");
    public ComboBox inputUnitComboBox;
    public ComboBox outputUnitComboBox;
    public TextField inputField;
    public TextField outputField;

    @FXML
    public void initialize(){
        Stage stage = new Stage();
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.TRANSPARENT);
        inputUnitComboBox.getItems().addAll(units);
        outputUnitComboBox.getItems().addAll(units);
        inputUnitComboBox.setValue("Picoseconds (ps)");
        outputUnitComboBox.setValue("Microseconds (μs)");
    }
    public void handleTimeConvert() {
        try{
            double inputValue = Double.parseDouble(inputField.getText().trim());
            String inputUnit = inputUnitComboBox.getValue().toString();
            String outputUnit = outputUnitComboBox.getValue().toString();
            Utilities utilities = new Utilities();
            double time = utilities.fromTimeConvert(inputValue, inputUnit);
            double result = utilities.toTimeConvert(time,outputUnit);
            outputField.setText(String.format("Result : %.6f",result));
        }catch (NumberFormatException e){
            outputField.setText("Invalid input.");
        }
    }
}
