package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ALL")
public class SpeedConverter {
    public TextField speedField;
    public ComboBox toSpeed;
    public ComboBox fromSpeed;
    public TextField outputField;
    private static final List<String> units = Arrays.asList("Lightspeed (c)", "Mach (Ma)", "Meter per second (m/s)", "Kilometer per hour (km/h)", "Kilometer per second (km/s)",
            "Knot (kn)", "Miles per hour (mph)", "Foot per second (fps)", "Inch per second (ips)");

    @FXML
    public void initialize(){
        fromSpeed.getItems().addAll(units);
        toSpeed.getItems().addAll(units);
        fromSpeed.setValue("Meter per second (m/s)");
        toSpeed.setValue("Kilometer per hour (km/h)");
    }

    public void handleSpeedConvert() {
        try {
            double inputValue = Double.parseDouble(speedField.getText().trim());
            String inputBase = fromSpeed.getValue().toString();
            String outputBase = toSpeed.getValue().toString();
            String convertedValue;
            Utilities utilities = new Utilities();
            double valueInMetersPerSecond = utilities.convertToMetersPerSecond(inputValue, inputBase);
            double result = utilities.convertFromMetersPerSecond(valueInMetersPerSecond, outputBase);
            outputField.setText(String.format("Result : %.6f",result));
        }catch (NumberFormatException e){
            outputField.setText("Invalid input");
        }
    }
}
