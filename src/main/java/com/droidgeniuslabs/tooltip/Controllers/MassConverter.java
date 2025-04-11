package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Arrays;
import java.util.List;

public class MassConverter {
    public TextField outputField;
    public ComboBox outputUnitComboBox;
    public ComboBox inputUnitComboBox;
    public TextField inputField;
    private static final List<String> units = Arrays.asList("Tonne (t)", "Kilogram (kg)", "Gram (g)", "Milligram (mg)", "Microgram  (μg)", "Quintal (q)", "Pound (lb)", "Ounce (oz)", "Carat (ct)",
     "Grain (gr)", "Long ton (l.t)", "Short ton (sh.t)", "UK hundredweight (cwt)", "US hundredweight (cwt)", "Stone (st)", "Dram (dr)", "Dan (dan)", "Jin (jin)", "Qian (qian)", "Liang (liang)", "Jin(Taiwan) (jin(tw)");

    @FXML
    public void initialize(){
        Stage stage = new Stage();
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.TRANSPARENT);
        inputUnitComboBox.getItems().addAll(units);
        outputUnitComboBox.getItems().addAll(units);
        inputUnitComboBox.setValue("kilogram (kg)");
        outputUnitComboBox.setValue("Gram (g)");
    }

    public void handleMassConvert() {
        try{
            double inputValue = Double.parseDouble(inputField.getText().trim());
            String fromUnit = inputUnitComboBox.getValue().toString();
            String toUnit = outputUnitComboBox.getValue().toString();
            Utilities utilities = new Utilities();
            double meters = utilities.fromMassConvert(inputValue, fromUnit);
            double result = utilities.toMassConvert(meters, toUnit);
            outputField.setText(String.format("%.6f", result));
        }catch (NumberFormatException e){
            outputField.setText("Invalid input");
        }
    }
}
