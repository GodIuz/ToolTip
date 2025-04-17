package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ColorPickerController {
    @FXML private ColorPicker colorPicker;
    @FXML private Pane colorPane;
    @FXML private TextField hexField;
    @FXML private Button copyButton;
    private final Utilities utilities = new Utilities();

    @FXML
    public void initialize() {
        colorPicker.setOnAction(event -> {
            Color selectedColor = colorPicker.getValue();
            String hex = utilities.colorToHex(selectedColor);
            hexField.setText(hex);
            colorPane.setStyle("-fx-background-color: " + hex + ";");
        });
        copyButton.setOnAction(event -> utilities.copyToClipboard());
    }

}
