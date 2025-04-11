package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BmiCalc {
    public TextField heightTextField;
    public TextField wightTextField;
    public Label resultLabel;

    @FXML
    public void calculateBMI() {
        try{
            double height =  Double.parseDouble(heightTextField.getText());
            double weight =  Double.parseDouble(wightTextField.getText());
            Utilities utilities = new Utilities();

            if(weight <=0 || height <=0 ){
                resultLabel.setText("The weight and height must be positive");
                resultLabel.setStyle("-fx-text-fill: orange;");
                return;
            }

            double bmi = utilities.bmiCalc(weight,height);
            String resultText;

            if (bmi < 18.5){
                resultText = String.format("BMI: %.2f . You are underweight",bmi);
                resultLabel.setStyle("-fx-text-fill: blue;");
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                resultText = String.format("BMI: %.2f . You are normal",bmi);
                resultLabel.setStyle("-fx-text-fill: green;");
            }else{
                resultText = String.format("BMI: %.2f . You are overweight",bmi);
                resultLabel.setStyle("-fx-text-fill: red;");
            }

            resultLabel.setText(resultText);

        }catch (Exception e){
            resultLabel.setText("Please enter valid numbers");
            resultLabel.setStyle("-fx-text-fill: darkred;");
        }
    }
}
