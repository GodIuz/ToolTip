package com.droidgeniuslabs.tooltip;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;

public class AreaConverter {
    public TextField inputAreaField;
    public ComboBox inputAreaUnitComboBox;
    public ComboBox outputAreaUnitComboBox;
    public TextField outputAreaField;
    private static final List<String> units = Arrays.asList("Square meter (m²)","Square kilometer (km²)", "Hectare (ha)", "Are (a)", "Square decimeter (dm²)",
            "Square centimeter (cm²)", "Square millimeter (mm²)", "Acre (ac)", "Square mile (mi²)", "Square yard (yd²)", "Square foot (ft²)", "Square inch (in²)",
            "Square rod (rd²)", "Qing (qing)" , "Mu (mu)", "Square chi (chi²)", "Square cun (cun²)", "Square gongli (gongli²)" ) ;

    @FXML
    public void initialize(){
        inputAreaUnitComboBox.getItems().addAll(units);
        outputAreaUnitComboBox.getItems().addAll(units);
        inputAreaUnitComboBox.setValue("Square meter (m²)");
        outputAreaUnitComboBox.setValue("Square kilometer (km²)");
    }
    public void handleAreaConvertButton() {
        try{
            double inputValue = Double.parseDouble(inputAreaField.getText().trim());
            String inputUnit = inputAreaUnitComboBox.getValue().toString();
            String outputUnit = outputAreaUnitComboBox.getValue().toString();
            Utilities utilities = new Utilities();
            double squareMeters = utilities.fromAreaConvert(inputValue, inputUnit);
            double result = utilities.toAreaConvert(squareMeters, outputUnit);
            outputAreaField.setText(String.format("Result : %.6f", result));
        }catch (NumberFormatException e){
            outputAreaField.setText("Invalid input");
        }
    }
}
