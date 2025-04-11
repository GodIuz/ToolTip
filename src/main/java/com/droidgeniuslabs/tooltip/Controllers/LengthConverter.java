package com.droidgeniuslabs.tooltip.Controllers;

import com.droidgeniuslabs.tooltip.Util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.util.LinkedHashMap;
import java.util.Map;

public class LengthConverter {
    private final Map<String, Double> units = new LinkedHashMap<>() {{
        put("kilometer (km)", 1000.0);
        put("meter (m)", 1.0);
        put("decimeter (dm)", 0.1);
        put("centimeter (cm)", 0.01);
        put("millimeter (mm)", 0.001);
        put("micrometer (μm)", 1e-6);
        put("nanometer (nm)", 1e-9);
        put("picometer (pm)", 1e-12);
        put("nautical mile (nmi)", 1852.0);
        put("mile (mi)", 1609.344);
        put("fathom (ftm)", 1.8288);
        put("yard (yd)", 0.9144);
        put("gongli (gongli)", 500.0);  // Assuming Gongli is 500 meters
        put("li (li)", 500.0);          // Assuming Li is 500 meters
        put("zhang (zhang)", 3.3);      // Assuming Zhang is 3.3 meters
        put("chi (chi)", 0.33);         // Assuming Chi is 0.33 meters
        put("cun (cun)", 0.033);        // Assuming Cun is 0.033 meters
        put("fen (fen)", 0.0033);       // Assuming Fen is 0.0033 meters
        put("lii (lii)", 0.00033);      // Assuming Lii is 0.00033 meters
        put("hao (hao)", 1e-5);         // Assuming Hao is 1e-5 meters
        put("parsec (pc)", 3.085677581e16);  // 1 parsec = 3.085677581 × 10^16 meters
        put("lunar distance (ld)", 384400000.0);  // Average distance from Earth to Moon in meters
        put("astronomical unit (AU)", 1.496e11); // 1 AU = 1.496 × 10^11 meters
        put("light year (ly)", 9.461e15); // 1 light year = 9.461 × 10^15 meters
    }};
    public TextField inputField;
    public ComboBox inputUnitComboBox;
    public ComboBox outputUnitComboBox;
    public TextField outputField;

    @FXML
    public void initialize(){
        inputUnitComboBox.getItems().addAll(units.keySet());
        outputUnitComboBox.getItems().addAll(units.keySet());
        inputUnitComboBox.setValue("meter (m)");
        outputUnitComboBox.setValue("kilometer (km)");
    }

    public void handleConvertButton(){
        try{
            double inputValue = Double.parseDouble(inputField.getText().trim());
            String fromUnit = inputUnitComboBox.getValue().toString();
            String toUnit = outputUnitComboBox.getValue().toString();
            Utilities utilities = new Utilities();
            double meters = utilities.fromDistanceConvert(inputValue, fromUnit);
            double result = utilities.toDistanceConvert(meters, toUnit);
            outputField.setText(String.format("%.6f", result));
        }catch (NumberFormatException e){
            outputField.setText("Invalid input");
        }
    }
}
