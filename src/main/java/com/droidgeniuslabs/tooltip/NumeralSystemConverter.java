package com.droidgeniuslabs.tooltip;

import com.droidgeniuslabs.tooltip.Util.Utilities;
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
    public void handleConvert() {
        String input = inputField.getText().trim();
        String inputBase = baseComboBox.getValue().toString();
        String outputBase = outbaseComboBox.getValue().toString();
        String convertedValue ;
        Utilities utilities = new Utilities();
        if (!input.isEmpty() && inputBase!=null &outputBase!=null){
            try{
                int decimalValue = utilities.convertToDecimal(input,inputBase);
                convertedValue = utilities.convertFromDecimal(decimalValue, outputBase);
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
