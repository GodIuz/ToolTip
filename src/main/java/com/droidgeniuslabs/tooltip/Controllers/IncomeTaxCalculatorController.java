package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Arrays;
import java.util.List;

public class IncomeTaxCalculatorController {
    public TextField incomeField;
    public Label taxLabel;
    public Label netIncomeLabel;
    public ComboBox countryComboBox;
    private final List<String> countries = Arrays.asList("Greece", "Cyprus", "USA", "United Kindom", "Germany");
    public Utilities utilities = new Utilities();

    public void initialize(){
	countryComboBox.getItems().addAll(countries);
	countryComboBox.setValue("Greece");
    }

    public void onCalculate(){
	try {
	    double income = Double.parseDouble(incomeField.getText());
	    String country = countryComboBox.getValue().toString();

	    if (country == null) {
		taxLabel.setText("Επιλέξτε χώρα.");
		netIncomeLabel.setText("");
		return;
	    }

	    double tax = switch (country) {
		case "Greece" -> utilities.calculateGreeceTax(income);
		case "Cyprus" -> utilities.calculateTaxCyprus(income);
		case "USA" -> utilities.calculateTaxUSA(income);
		case "United Kindom" -> utilities.calculateTaxUK(income);
		case "Germany" -> utilities.calculateTaxGermany(income);
		default -> 0;
	    };

	    taxLabel.setText(String.format("Φόρος: %.2f €", tax));
	    netIncomeLabel.setText(String.format("Καθαρό Εισόδημα: %.2f €", income - tax));

	} catch (NumberFormatException e) {
	    taxLabel.setText("Μη έγκυρο εισόδημα.");
	    netIncomeLabel.setText("");
	}
    }
}
