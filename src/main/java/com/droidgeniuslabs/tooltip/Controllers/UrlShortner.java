package com.droidgeniuslabs.tooltip.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UrlShortner {
    @FXML private TextField originalUrl;
    @FXML private TextField shortenedUrl;

    @FXML
    public void shortenUrl() {
        String url = originalUrl.getText();
        shortenedUrl.setText("https://short.url/" + url.hashCode());
    }
}
