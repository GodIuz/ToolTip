package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CurrencyConverter {

    @FXML
    public TextField amountField;

    @FXML
    public ComboBox<String> fromCurrency;

    @FXML
    public ComboBox<String> toCurrency;

    @FXML
    public Label resultLabel;

    @FXML
    public ProgressIndicator loadingIndicator;

    private static final List<String> currencies = Arrays.asList("USD", "EUR", "GBP", "INR", "JPY", "CAD", "AUD", "CNY", "CHF");

    @FXML
    public void initialize() {
        Stage stage = new Stage();
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.TRANSPARENT);
        fromCurrency.getItems().addAll(currencies);
        toCurrency.getItems().addAll(currencies);
        fromCurrency.setValue("USD");
        toCurrency.setValue("EUR");
        loadingIndicator.setVisible(false); // hide initially
    }

    public void handleCurrencyConverter() {
        String from = fromCurrency.getValue();
        String to = toCurrency.getValue();
        String amountText = amountField.getText();

        if (from == null || to == null || amountText.isEmpty()) {
            resultLabel.setText("Please fill in all fields.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            loadingIndicator.setVisible(true);
            resultLabel.setText("Loading...");

            Task<Double> task = new Task<>() {
                @Override
                protected Double call() throws IOException {
                    Utilities utilities = new Utilities();
                    return utilities.getExchangeRate(from, to);
                }
            };

            task.setOnSucceeded(_ -> {
                double rate = task.getValue();
                if (rate == -1) {
                    resultLabel.setText("Could not retrieve rate.");
                } else {
                    double converted = amount * rate;
                    resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, from, converted, to));
                }
                loadingIndicator.setVisible(false);
            });

            task.setOnFailed(_ -> {
                resultLabel.setText("Error during conversion.");
                loadingIndicator.setVisible(false);
                task.getException().printStackTrace();
            });

            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();

        } catch (NumberFormatException e) {
            resultLabel.setText("Enter a valid number.");
        }
    }
}
