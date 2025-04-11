package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DiscountCalc {
    public TextField originalPriceTextField;
    public TextField discountField;
    public Label finalPriceLabel;
    public void calculateDiscount() {
        Stage stage = new Stage();
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.TRANSPARENT);
        try{
            double initialPrice = Double.parseDouble(originalPriceTextField.getText());
            double discountPercent = Double.parseDouble(discountField.getText());
            Utilities utilities = new Utilities();
            if (initialPrice < 0 || discountPercent < 0 || discountPercent > 100) {
                finalPriceLabel.setText("Give valid positive values. The percentage ≤ 100.");
                finalPriceLabel.setTextFill(Color.ORANGE);
                return;
            }
            double finalPrice = utilities.discountCalc(initialPrice,discountPercent);
            finalPriceLabel.setText(String.format("Final Price : %.2f €", finalPrice));
            finalPriceLabel.setTextFill(Color.GREEN);
        } catch (NumberFormatException e) {
            finalPriceLabel.setText("Please enter numbers only.");
            finalPriceLabel.setTextFill(Color.RED);
        }
    }
}
