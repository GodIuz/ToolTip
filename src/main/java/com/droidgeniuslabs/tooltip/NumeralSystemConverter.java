package com.droidgeniuslabs.tooltip;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class NumeralSystemConverter {

    public TextField inputField;
    public ComboBox baseComboBox;
    public Label resultLabel;
    private static final List<String> units = Arrays.asList("Binary", "Octal", "Decimal", "Hexadecimal");
    @FXML
    private ComboBox outbaseComboBox;

    @FXML
    public void initialize() {
        baseComboBox.getItems().addAll(units);
        outbaseComboBox.getItems().addAll(units);
        baseComboBox.setValue("Binary");
        outbaseComboBox.setValue("Decimal");
    }
    private int convertToDecimal(String input, String base) {
        int decimalValue = 0;

        switch (base) {
            case "Binary":
                decimalValue = Integer.parseInt(input, 2);
                break;
            case "Octal":
                decimalValue = Integer.parseInt(input, 8);
                break;
            case "Decimal":
                decimalValue = Integer.parseInt(input);
                break;
            case "Hexadecimal":
                decimalValue = Integer.parseInt(input, 16);
                break;
        }

        return decimalValue;
    }
    private String convertFromDecimal(int decimalValue, String base) {
        switch (base) {
            case "Binary":
                return Integer.toBinaryString(decimalValue);
            case "Octal":
                return Integer.toOctalString(decimalValue);
            case "Decimal":
                return Integer.toString(decimalValue);
            case "Hexadecimal":
                return Integer.toHexString(decimalValue).toUpperCase();
            default:
                return "Invalid base";
        }
    }
    public void handleConvert() {
        String input = inputField.getText().trim();
        String inputBase = baseComboBox.getValue().toString();
        String outputBase = outbaseComboBox.getValue().toString();
        String convertedValue ;
        if (!input.isEmpty() && inputBase!=null &outputBase!=null){
            try{
                int decimalValue = convertToDecimal(input,inputBase);
                convertedValue = convertFromDecimal(decimalValue, outputBase);
            }catch (NumberFormatException e){
                convertedValue = "Invalid input for the selected base";
                resultLabel.setTextFill(Color.RED);
            }
        }else{
            convertedValue = "Please provide input for the selected base";
            resultLabel.setTextFill(Color.RED);
        }
        resultLabel.setText(convertedValue);
    }
}
