package com.droidgeniuslabs.tooltip.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class TextCompressor {
    @FXML private TextArea inputText;
    @FXML private TextArea outputText;

    @FXML
    public void compressText() {
        String text = inputText.getText();
        outputText.setText(text.replaceAll("(.)\\1+", "$1")); // simple demo compression
    }

    @FXML
    public void decompressText() {
        // Just for demonstration
        outputText.setText("Decompression logic needed");
    }
}
