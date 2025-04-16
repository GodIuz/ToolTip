package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Arrays;
import java.util.List;

public class VatCalculatorController {
    @FXML public ComboBox vatRateComboBox;
    @FXML public TextField amountField;
    @FXML public Label resultLabel;
    @FXML public ToggleGroup vatModeToggle;
    private final List<String> vatTypes = Arrays.asList("6","13","24");
    @FXML public RadioButton removeVatRadio;
    @FXML public RadioButton addVatRadio;
    public Utilities utilities = new Utilities();
    @FXML
    public void initialize(){
	vatRateComboBox.getItems().addAll(vatTypes);
	vatRateComboBox.getSelectionModel().select(2);
	vatModeToggle = new ToggleGroup();
	addVatRadio.setToggleGroup(vatModeToggle);
	removeVatRadio.setToggleGroup(vatModeToggle);
    }
    public void onCalculate() {
	try{
	    double amount = Double.parseDouble(amountField.getText());
	    double vatRate = Double.parseDouble(vatRateComboBox.getValue().toString());
	    RadioButton selected = (RadioButton) vatModeToggle.getSelectedToggle();
	    if(selected == null){
		resultLabel.setText("Choose Vat functionality");
	    }
	    double result;
	    assert selected != null;
	    if (selected.getText().equals("Προσθήκη ΦΠΑ")) {
		result = utilities.addTax(amount,vatRate);
		resultLabel.setText(String.format("Τελική Τιμή: %.2f €", result));
	    } else {
		result = utilities.removeTax(amount,vatRate);
		resultLabel.setText(String.format("Καθαρή Τιμή: %.2f €", result));
	    }

	} catch (NumberFormatException e) {
	    resultLabel.setText("Invalid input");
	}
    }
}
