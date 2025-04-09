package com.droidgeniuslabs.tooltip;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloController {
    private final Map<String, Stage> openStages = new HashMap<>();
    public Button areaButton;
    public Button bmiButton;
    public Button dataButton;
    public Button discountButton;
    public Button lengthButton;
    public Button massButton;
    public Button numeralSystemButton;
    public Button speedButton;
    public Button temperatureButton;
    public Button timeButton;

    private void openWindow(String fxmlPath, String title) {
        Stage existingStage = openStages.get(title);

        if (existingStage == null || !existingStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                Parent root = loader.load();

                Stage newStage = new Stage();
                newStage.setTitle(title);
                newStage.setScene(new Scene(root));
                newStage.setAlwaysOnTop(true);
                // Cleanup on close
                newStage.setOnCloseRequest(e -> openStages.remove(title));

                openStages.put(title, newStage);
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            existingStage.toFront(); // Bring existing window to front
            if (existingStage.isIconified()) {
                existingStage.setIconified(false); // Restore if minimized
            }
        }
    }

    public void openAreaConverter() {
        openWindow("are_converter.fxml","Area Converter");
    }

    public void openBMICalc() {
        openWindow("bmi_calc.fxml", "BMI Calc");
    }

    public void openDataConverter() {
        openWindow("data_converter.fxml", "Data Converter");
    }

    public void openDiscountCalc() {
        openWindow("discount_calc.fxml", "Discount Calc");
    }

    public void openLengthConverter() {
        openWindow("length_converter.fxml","Length Converter");
    }

    public void openMassConverter() {
        openWindow("mass_converter.fxml", "Mass Converter");
    }

    public void openNumeralSystemConverter(){
        openWindow("numeral_system_converter.fxml", "Numeral System Converter");
    }

    public void openSpeedConverter(){
        openWindow("speed_converter.fxml", "Speed Converter");
    }

    public void openTemperatureConverter(){
        openWindow("temperature_converter.fxml", "Temperature Converter");
    }

    public void openTimeConverter(){
        openWindow("time.fxml", "Time Converter");
    }

    public void openVolumeConverter(){
        openWindow("volume_converter.fxml", "Temperature Converter");
    }
}