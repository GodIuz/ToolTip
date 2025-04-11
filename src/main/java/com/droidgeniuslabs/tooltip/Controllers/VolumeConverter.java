package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Arrays;
import java.util.List;

public class VolumeConverter {
    private static final List<String> units = Arrays.asList("Cubic meter (m³)", "Cubic decimeter (dm³)", "Cubic centimeter (cm³)", "Cubic millimeter (mm³)",
            "Hectoliter (hl)", "Liter (l)", "Deciliter (dl)", "Centiliter (cl)", "Milliliter (ml)",
            "Cubic foot (ft³)", "Cubic inch (in³)", "Cubic yard (yd³)", "Acre-foot (af³)",
            "US gallon (gal)", "Imperial gallon (imp gal)");
    public TextField outputVolumeField;
    public ComboBox outputVolumeUnitComboBox;
    public ComboBox inputVolumeUnitComboBox;
    public TextField inputVolumeField;

    @FXML
    public void initialize(){
        Stage stage = new Stage();
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.TRANSPARENT);
        inputVolumeUnitComboBox.getItems().addAll(units);
        outputVolumeUnitComboBox.getItems().addAll(units);
        inputVolumeUnitComboBox.setValue("Liter (l)");
        outputVolumeUnitComboBox.setValue("Milliliter (ml)");
    }
    public void handleAreaConvertButton() {
        try {
            double inputValue = Double.parseDouble(inputVolumeField.getText().trim());
            String fromUnit = inputVolumeUnitComboBox.getValue().toString();
            String toUnit = outputVolumeUnitComboBox.getValue().toString();
            Utilities utilities = new Utilities();
            double  valueInLiters = utilities.toLiters(fromUnit, inputValue);
            double result = utilities.fromLiters(toUnit, valueInLiters);
            outputVolumeField.setText(String.format("Result : %.6f",result));
        } catch (Exception e) {
            outputVolumeField.setText("Invalid input");
        }
    }
}
