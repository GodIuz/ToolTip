package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ColorPickerController {
    @FXML private ColorPicker colorPicker;
    @FXML private TextField hexField;
    @FXML private TextField rgbField;
    @FXML private TextField rgbaField;
    @FXML private Button copyHexButton;
    @FXML private Button copyRgbButton;
    @FXML private Button copyRgbaButton;
    @FXML private Button copyAllButton;
    @FXML private Label copiedLabel;
    @FXML private ListView<String> colorHistoryList;
    @FXML private Pane colorPane;
    private final Utilities utilities = new Utilities();

    @FXML
    public void initialize() {
        // Color Picker Listener
        colorPicker.setOnAction(e -> {
            Color selectedColor = colorPicker.getValue();
            utilities.updateColor(selectedColor, hexField, rgbField, rgbaField, colorPane);

            // Add to history if not already present
            String hex = Utilities.colorToHex(selectedColor);
            if (!colorHistoryList.getItems().contains(hex)) {
                colorHistoryList.getItems().add(hex);
            }
        });

        // Copy buttons
        copyHexButton.setOnAction(e -> {
            Utilities.copyToClipboard(hexField.getText());
            showCopiedMessage();
        });

        copyRgbButton.setOnAction(e -> {
            Utilities.copyToClipboard(rgbField.getText());
            showCopiedMessage();
        });

        copyRgbaButton.setOnAction(e -> {
            Utilities.copyToClipboard(rgbaField.getText());
            showCopiedMessage();
        });

        copyAllButton.setOnAction(e -> {
            Utilities.copyAll(hexField.getText(), rgbField.getText(), rgbaField.getText());
            showCopiedMessage();
        });

    }

    private void showCopiedMessage() {
        copiedLabel.setVisible(true);
        new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ignored) {}
            copiedLabel.setVisible(false);
        }).start();
    }
}