package com.droidgeniuslabs.tooltip.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class FileBase64 {
    @FXML private Label fileNameLabel;
    @FXML private TextArea base64Output;

    @FXML
    public void chooseFile() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        if (file != null) {
            fileNameLabel.setText(file.getName());
            try {
                byte[] bytes = Files.readAllBytes(file.toPath());
                base64Output.setText(Base64.getEncoder().encodeToString(bytes));
            } catch (IOException e) {
                base64Output.setText("Error: " + e.getMessage());
            }
        }
    }

    @FXML
    public void copyBase64() {
        base64Output.selectAll();
        base64Output.copy();
    }
}
