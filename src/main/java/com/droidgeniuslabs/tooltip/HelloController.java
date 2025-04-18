package com.droidgeniuslabs.tooltip;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class HelloController {
    private final Map<String, Stage> openStages = new HashMap<>();
    public Button areaButton;
    public Button bmiButton;
    public Button dataButton;
    public Button discountButton;
    public Button lengthButton;
    public Button massButton;
    public Button numeralSystemButton;
    public Button speedButton;
    public Button temperatureButton;
    public Button timeButton;
    public Button volumeButton;
    public Label aboutLabel;
    public Button currencyButton;
    public Button vatButton;
    public Button incomeVatCalculator;
    public Button calendarButton;
    public Button calculatorButton;

    private void openWindow(String fxmlPath, String title) {
        try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
              Parent root = loader.load();
              Stage newStage = new Stage();
              newStage.setTitle(title);
              newStage.setScene(new Scene(root));
              newStage.setAlwaysOnTop(true);
              newStage.setOnCloseRequest(_ -> openStages.remove(title));
              openStages.put(title, newStage);
              newStage.show();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    public void openAreaConverter() {
        openWindow("area_converter.fxml","Area Converter");
    }
    public void openBMICalc() {
        openWindow("bmi_calc.fxml", "BMI Calc");
    }
    public void openDataConverter() {
        openWindow("data_converter.fxml", "Data Converter");
    }
    public void openDiscountCalc() {
        openWindow("discount_calc.fxml", "Discount Calc");
    }
    public void openLengthConverter() {
        openWindow("length_converter.fxml","Length Converter");
    }
    public void openMassConverter() {
        openWindow("mass_converter.fxml", "Mass Converter");
    }
    public void openNumeralSystemConverter(){
        openWindow("numeral_system_converter.fxml", "Numeral System Converter");
    }
    public void openSpeedConverter(){
        openWindow("speed_converter.fxml", "Speed Converter");
    }
    public void openTemperatureConverter(){
        openWindow("temperature_converter.fxml", "Temperature Converter");
    }
    public void openTimeConverter(){
        openWindow("time_converter.fxml", "Time Converter");
    }
    public void openVolumeConverter(){
        openWindow("volume_converter.fxml", "Volume Converter");
    }
    public void openAboutLink() {
        try{
            Desktop.getDesktop().browse(new URI("https://github.com/GodIuz"));
            Desktop.getDesktop().browse(new URI("https://dev.notyezz.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void openCurrencyConverter() {
        openWindow("currency_converter.fxml", "Currency Converter");
    }
    public void openVatCalculator(){openWindow("vat_calc.fxml", "Vat Calcualtor");}
    @FXML
    private void onOpenIncomeTaxCalculator() {
        openWindow("income_tax_calculator.fxml", "Υπολογιστής Φόρου Εισοδήματος");
    }
    public void onOpenTimeZoneConverter() {
        openWindow("timezone_converter.fxml","TimeZone Converter");
    }
    public void onOpenUnixTimestampConverter() {
        openWindow("unix_timestamp_converter.fxml","Unix Timestamp Converter");
    }
    public void onOpenFuelConverter(){openWindow("fuel_converter.fxml","Fuel Converter");}
    public void onOpenAgeCalculator(){openWindow("age_calculator.fxml","Age Calculator");}
    public void onOpenPowerConsumptionCalculator(){openWindow("power_consumption_calculator.fxml","Power Consumption Calculator");}
    public void onOpenTextBinaryConverter() {
        openWindow("text_binary_converter.fxml","Text Binary Converter");
    }
    public void onopenBarcodeGenerator(){openWindow("barcode_generator.fxml","Barcode Generator");}
    public void onOpenCalendar(){openWindow("calendar_calculator.fxml","Calendar Calculator");}
    public void onOpenCalculator(){openWindow("calculator.fxml","Calculator");}
    public void onOpenPressureConverter(){
        openWindow("pressure_converter.fxml","Pressure Converter");
    }
    public void onOpenTextCompressorController(){
        openWindow("text_compresssor.fxml","Text Compressor");
    }
    public void onOpenColorPicker(){
        openWindow("color_picker.fxml","Color Picker");
    }
    public void onOpenExtEncryption(){
        openWindow("ext_encryption.fxml", "Encryption Tool");
    }
    public void onOpenUrlShortner(){
        openWindow("url_shortner.fxml", "URL Shortner");
    }
    public void onOpenFileBase64(){
        openWindow("file_base64.fxml","File Base64");
    }
    public void onOpenTriviaGame(){openWindow("trivia_game.fxml", "Trivia Game");}
    public void onOpenDateCalculator() {
        openWindow("date_calculator.fxml", "Date Calculator");
    }
    public void onOpenInterestCalculator() {openWindow("interest_calculator.fxml", "Interest Calculator");}
    public void onOpenTicTacToe() {
        openWindow("tic_tac_toe.fxml", "Tic Tac Toe");
    }
}