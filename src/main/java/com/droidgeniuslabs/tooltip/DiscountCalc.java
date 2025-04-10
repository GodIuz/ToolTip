package com.droidgeniuslabs.tooltip;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class DiscountCalc {
    public TextField originalPriceTextField;
    public TextField discountField;
    public Label finalPriceLabel;
    public void calculateDiscount() {
        try{
            double initialPrice = Double.parseDouble(originalPriceTextField.getText());
            double discountPercent = Double.parseDouble(discountField.getText());

            if (initialPrice < 0 || discountPercent < 0 || discountPercent > 100) {
                finalPriceLabel.setText("Give valid positive values. The percentage ≤ 100.");
                finalPriceLabel.setTextFill(Color.ORANGE);
                return;
            }

            double discountAmount = initialPrice * (discountPercent / 100);
            double finalPrice = initialPrice - discountAmount;

            finalPriceLabel.setText(String.format("Final Price: %.2f €", finalPrice));
            finalPriceLabel.setTextFill(Color.GREEN);
        } catch (NumberFormatException e) {
            finalPriceLabel.setText("Please enter numbers only.");
            finalPriceLabel.setTextFill(Color.RED);
        }
    }
}
