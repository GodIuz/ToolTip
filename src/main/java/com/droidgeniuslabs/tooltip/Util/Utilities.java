package com.droidgeniuslabs.tooltip.Util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.animation.PauseTransition;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Utilities {
    private final String API_KEY = "acecfc556779dc60b6992973";
    private final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";
    private BufferedImage qrImage;
    private Label scanResultLabel;
    private Label copiedLabel;
    private final double PA = 1.0;
    private final double BAR = 100000.0;
    private final double ATM = 101325.0;
    private final double PSI = 6894.76;
    private ColorPicker colorPicker;
    private Pane colorPane;
    private TextField hexField;
    private TextField rgbField;
    private TextField daysInputField;
    private DatePicker baseDatePicker;
    private Label resultDateLabel;


    public double convertToBytes(double value, @NotNull String unit) {
        switch (unit.toLowerCase()) {
            case "kilobytes":
                return value * 1024;
            case "megabytes":
                return value * 1024 * 1024;
            case "gigabytes":
                return value * 1024 * 1024 * 1024;
            case "terabytes":
                return value * 1024 * 1024 * 1024 * 1024;
            case "petabytes":
                return value * 1024 * 1024 * 1024 * 1024 * 1024;
            case "bytes":
            default:
                return value;
        }
    }
    public double convertFromBytes(double value, @NotNull String unit) {
        switch (unit.toLowerCase()) {
            case "kilobytes":
                return value / 1024;
            case "megabytes":
                return value / (1024 * 1024);
            case "gigabytes":
                return value / (1024 * 1024 * 1024);
            case "terabytes":
                return value / (1024 * 1024 * 1024 * 1024);
            case "petabytes":
                return value / (1024 * 1024 * 1024 * 1024 * 1024);
            case "bytes":
            default:
                return value;
        }
    }
    public int convertToDecimal(String input, @NotNull String base) {
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
    public String convertFromDecimal(int decimalValue, @NotNull String base) {
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
    public double toCelsius(double temp, @NotNull String from) {
        return switch (from) {
            case "Celsius" -> temp;
            case "Fahrenheit" -> (temp - 32) * 5 / 9;
            case "Kelvin" -> temp - 273.15;
            case "Rankine" -> (temp - 491.67) * 5 / 9;
            case "Reaumur" -> temp * 1.25;
            default -> throw new IllegalArgumentException("Unknown unit: " + from);
        };
    }
    public double fromCelsius(double temp, @NotNull String to) {
        return switch (to) {
            case "Celsius" -> temp;
            case "Fahrenheit" -> (temp * 9 / 5) + 32;
            case "Kelvin" -> temp + 273.15;
            case "Rankine" -> (temp + 273.15) * 9 / 5;
            case "Reaumur" -> temp * 0.8;
            default -> throw new IllegalArgumentException("Unknown unit: " + to);
        };
    }
    public double convertToMetersPerSecond(double value, @NotNull String unit) {
        return switch (unit) {
            case "Meter per second (m/s)" -> value;
            case "Kilometer per hour (km/h)" -> value / 3.6;
            case "Kilometer per second (km/s)" -> value * 1000;
            case "Miles per hour (mph)" -> value * 0.44704;
            case "Knot (kn)" -> value * 0.514444;
            case "Foot per second (fps)" -> value * 0.3048;
            case "Inch per second (ips)" -> value * 0.0254;
            case "Mach (Ma)" -> value * 343;
            case "Lightspeed (c)" -> value * 299_792_458;
            default -> throw new IllegalArgumentException("Unknown unit: " + unit);
        };
    }
    public double convertFromMetersPerSecond(double value, @NotNull String unit) {
        return switch (unit) {
            case "Meter per second (m/s)" -> value;
            case "Kilometer per hour (km/h)" -> value * 3.6;
            case "Kilometer per second (km/s)" -> value / 1000;
            case "Miles per hour (mph)" -> value / 0.44704;
            case "Knot (kn)" -> value / 0.514444;
            case "Foot per second (fps)" -> value / 0.3048;
            case "Inch per second (ips)" -> value / 0.0254;
            case "Mach (Ma)" -> value / 343;
            case "Lightspeed (c)" -> value / 299_792_458;
            default -> throw new IllegalArgumentException("Unknown unit: " + unit);
        };
    }
    public double bmiCalc(double weight, double height){
        return weight / (height * height);
    }
    public double discountCalc(double initialPrice, double discountPercent){
        double discountAmount = initialPrice * (discountPercent / 100);
        double finalPrice = initialPrice - discountAmount;
        return finalPrice;
    }
    public double fromAreaConvert(double value, @NotNull String unit) {
        return switch (unit) {
            case "Square meter (m²)" -> value;
            case "Square kilometer (km²)" -> value * 1_000_000;
            case "Hectare (ha)" -> value * 10_000;
            case "Are (a)" -> value * 100;
            case "Square decimeter (dm²)" -> value * 0.01;
            case "Square centimeter (cm²)" -> value * 0.0001;
            case "Square millimeter (mm²)" -> value * 0.000001;
            case "Acre (ac)" -> value * 4046.8564224;
            case "Square mile (mile²)" -> value * 2_589_988.110336;
            case "Square yard (yd²)" -> value * 0.83612736;
            case "Square foot (ft²)" -> value * 0.09290304;
            case "Square inch (in²)" -> value * 0.00064516;
            case "Square rod (rd²)" -> value * 25.29285264;
            case "Qing (qing)" -> value * 6_666_666.6667;
            case "Mu (mu)" -> value * 666.6666667;
            case "Square chi (chi²)" -> value * 0.1111111111;
            case "Square cun (cun²)" -> value * 0.0011111111;
            case "Square gongli (gongli²)" -> value * 1_000_000; // same as km²
            default -> throw new IllegalArgumentException("Unknown unit: " + unit);
        };
    }
    public double toAreaConvert(double value, @NotNull String unit) {
        return switch (unit) {
            case "Square meter (m²)" -> value;
            case "Square kilometer (km²)" -> value / 1_000_000;
            case "Hectare (ha)" -> value / 10_000;
            case "Are (a)" -> value / 100;
            case "Square decimeter (dm²)" -> value / 0.01;
            case "Square centimeter (cm²)" -> value / 0.0001;
            case "Square millimeter (mm²)" -> value / 0.000001;
            case "Acre (ac)" -> value / 4046.8564224;
            case "Square mile (mile²)" -> value / 2_589_988.110336;
            case "Square yard (yd²)" -> value / 0.83612736;
            case "Square foot (ft²)" -> value / 0.09290304;
            case "Square inch (in²)" -> value / 0.00064516;
            case "Square rod (rd²)" -> value / 25.29285264;
            case "Qing (qing)" -> value / 6_666_666.6667;
            case "Mu (mu)" -> value / 666.6666667;
            case "Square chi (chi²)" -> value / 0.1111111111;
            case "Square cun (cun²)" -> value / 0.0011111111;
            case "Square gongli (gongli²)" -> value / 1_000_000; // same as km²
            default -> throw new IllegalArgumentException("Unknown unit: " + unit);
        };
    }
    public double fromTimeConvert(double value, @NotNull String unit) {
        return switch (unit) {
            case "Picoseconds (ps)" -> value * 0.000000000001;
            case "Microseconds (μs)" -> value * 0.000001;
            case "Milliseconds (ms)" -> value * 0.001;
            case "Second (s)" -> value;
            case "Minute (m)" -> value * 60;
            case "Hour (h)" -> value * 3600;
            case "Day (d)" -> value * 86400;
            case "Week (wk)" -> value * 604800;
            case "Year (y)" -> value * 31_536_000;
            default -> throw new IllegalArgumentException("Unknown unit: " + unit);
        };
    }
    public double toTimeConvert(double value, @NotNull String unit) {
        return switch (unit) {
            case "Picoseconds (ps)" -> value / 1e-12;
            case "Microseconds (μs)" -> value / 1e-6;
            case "Milliseconds (ms)" -> value / 1e-3;
            case "Second (s)" -> value;
            case "Minute (m)" -> value / 60;
            case "Hour (h)" -> value / 3600;
            case "Day (d)" -> value / 86400;
            case "Week (wk)" -> value / 604800;
            case "Year (y)" -> value / 31_536_000;
            default -> throw new IllegalArgumentException("Unknown unit: " + unit);
        };
    }
    public double fromDistanceConvert(double value, @NotNull String unit) {
        return switch (unit) {
            case "kilometer (km)" -> value * 1000;
            case "meter (m)" -> value;
            case "decimeter (dm)" -> value * 0.1;
            case "centimeter (cm)" -> value * 0.01;
            case "millimeter (mm)" -> value * 0.001;
            case "micrometer (μm)" -> value * 1e-6;
            case "nanometer (nm)" -> value * 1e-9;
            case "picometer (pm)" -> value * 1e-12;
            case "nautical mile (nmi)" -> value * 1852;
            case "mile (mi)" -> value * 1609.344;
            case "fathom (ftm)" -> value * 1.8288;
            case "yard (yd)" -> value * 0.9144;
            case "gongli (gongli)" -> value * 500;
            case "li (li)" -> value * 500;
            case "zhang (zhang)" -> value * 3.3;
            case "chi (chi)" -> value * 0.33;
            case "cun (cun)" -> value * 0.033;
            case "fen (fen)" -> value * 0.0033;
            case "lii (lii)" -> value * 0.00033;
            case "hao (hao)" -> value * 1e-5;
            case "parsec (pc)" -> value * 3.085677581e16;
            case "lunar distance (ld)" -> value * 384400000;
            case "astronomical unit (AU)" -> value * 1.496e11;
            case "light year (ly)" -> value * 9.461e15;
            default -> throw new IllegalArgumentException("Unknown unit: " + unit);
        };
    }
    public double toDistanceConvert(double value, @NotNull String unit) {
        return switch (unit) {
            case "kilometer (km)" -> value / 1000;
            case "meter (m)" -> value;
            case "decimeter (dm)" -> value / 0.1;
            case "centimeter (cm)" -> value / 0.01;
            case "millimeter (mm)" -> value / 0.001;
            case "micrometer (μm)" -> value / 1e-6;
            case "nanometer (nm)" -> value / 1e-9;
            case "picometer (pm)" -> value / 1e-12;
            case "nautical mile (nmi)" -> value / 1852;
            case "mile (mi)" -> value / 1609.344;
            case "fathom (ftm)" -> value / 1.8288;
            case "yard (yd)" -> value / 0.9144;
            case "gongli (gongli)" -> value / 500;
            case "li (li)" -> value / 500;
            case "zhang (zhang)" -> value / 3.3;
            case "chi (chi)" -> value / 0.33;
            case "cun (cun)" -> value / 0.033;
            case "fen (fen)" -> value / 0.0033;
            case "lii (lii)" -> value / 0.00033;
            case "hao (hao)" -> value / 1e-5;
            case "parsec (pc)" -> value / 3.085677581e16;
            case "lunar distance (ld)" -> value / 384400000;
            case "astronomical unit (AU)" -> value / 1.496e11;
            case "light year (ly)" -> value / 9.461e15;
            default -> throw new IllegalArgumentException("Unknown unit: " + unit);
        };
    }
    public double fromMassConvert(double value, @NotNull String unit){
        return switch (unit){
            case "Tonne (t)" -> value * 1000;
            case "Kilogram (kg)" -> value;
            case "Gram (g)" -> value * 0.001;
            case "Milligram (mg)" -> value * 0.000001;
            case "Microgram (μg)" -> value * 0.000000001;
            case "Quintal (q)" -> value * 100;
            case "Ounce (oz)" -> value * 0.0283495;
            case "Pound (lb)" -> value * 0.453592;
            case "Carat (ct)" -> value * 0.0002;
            case "Grain (gr)" -> value * 0.0000648;
            case "Long ton (l.t)" -> value * 1016.05;
            case "Short ton (sh.t)" -> value * 907.184;
            case "UK hundredweight (cwt)" -> value * 50.8;
            case "US hundredweight (cwt)" -> value * 45.3592;
            case "Stone (st)" -> value * 6.35029;
            case "Dram (dr)" -> value * 0.001771;
            case "Dan (dan)" -> value * 50;
            case "Jin (jin)" -> value * 0.5;
            case "Qian (qian)" -> value * 0.05;
            case "Liang (liang)" -> value * 0.5;
            default -> throw new IllegalArgumentException("Unknown unit" + unit);
        };
    }
    public double toMassConvert(double value, @NotNull String unit){
        return switch (unit){
            case "Tonne (t)" -> value / 1000;
            case "Kilogram (kg)" -> value;
            case "Gram (g)" -> value / 0.001;
            case "Milligram (mg)" -> value / 0.000001;
            case "Microgram (μg)" -> value / 0.000000001;
            case "Quintal (q)" -> value / 100;
            case "Ounce (oz)" -> value / 0.0283495;
            case "Pound (lb)" -> value / 0.453592;
            case "Carat (ct)" -> value / 0.0002;
            case "Grain (gr)" -> value / 0.0000648;
            case "Long ton (l.t)" -> value / 1016.05;
            case "Short ton (sh.t)" -> value / 907.184;
            case "UK hundredweight (cwt)" -> value / 50.8;
            case "US hundredweight (cwt)" -> value / 45.3592;
            case "Stone (st)" -> value / 6.35029;
            case "Dram (dr)" -> value / 0.001771;
            case "Dan (dan)" -> value / 50;
            case "Jin (jin)" -> value / 0.5;
            case "Qian (qian)" -> value / 0.05;
            case "Liang (liang)" -> value / 0.5;
            default -> throw new IllegalArgumentException("Unknown unit" + unit);
        };
    }
    public double toLiters(@NotNull String unit, double value) {
        switch (unit) {
            case "Cubic meter (m³)": return value * 1000;
            case "Cubic decimeter (dm³)": return value;
            case "Cubic centimeter (cm³)": return value / 1000;
            case "Cubic millimeter (mm³)": return value / 1_000_000;
            case "Hectoliter (hl)": return value * 100;
            case "Liter (l)": return value;
            case "Deciliter (dl)": return value / 10;
            case "Centiliter (cl)": return value / 100;
            case "Milliliter (ml)": return value / 1000;
            case "Cubic foot (ft³)": return value * 28.3168;
            case "Cubic inch (in³)": return value * 0.0163871;
            case "Cubic yard (yd³)": return value * 764.555;
            case "Acre-foot (af³)": return value * 1_233_481.84;
            case "US gallon (gal)": return value * 3.78541;
            case "Imperial gallon (imp gal)": return value * 4.54609;
            default: return value;
        }
    }
    public double fromLiters(@NotNull String unit, double value) {
        switch (unit) {
            case "Cubic meter (m³)": return value / 1000;
            case "Cubic decimeter (dm³)": return value;
            case "Cubic centimeter (cm³)": return value * 1000;
            case "Cubic millimeter (mm³)": return value * 1_000_000;
            case "Hectoliter (hl)": return value / 100;
            case "Liter (l)": return value;
            case "Deciliter (dl)": return value * 10;
            case "Centiliter (cl)": return value * 100;
            case "Milliliter (ml)": return value * 1000;
            case "Cubic foot (ft³)": return value / 28.3168;
            case "Cubic inch (in³)": return value / 0.0163871;
            case "Cubic yard (yd³)": return value / 764.555;
            case "Acre-foot (af³)": return value / 1_233_481.84;
            case "US gallon (gal)": return value / 3.78541;
            case "Imperial gallon (imp gal)": return value / 4.54609;
            default: return value;
        }
    }
    public double getExchangeRate(String from, String to) throws IOException {
        URL url = new URL(API_URL + "/" + from);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int status = httpURLConnection.getResponseCode();
        if(status != 200){
            return -1;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;
        while((inputLine = bufferedReader.readLine())!=null){
            stringBuilder.append(inputLine);
        }
        bufferedReader.close();
        JSONObject jsonpObject = new JSONObject(stringBuilder.toString());
        JSONObject rates = jsonpObject.getJSONObject("conversion_rates");
        return rates.getDouble(to);
    }
    public double addTax(double amount, double vatRate){
	return amount * (1 + vatRate / 100);
    }
    public double removeTax(double amount, double vatRate) {
        return amount / (1 + vatRate / 100);
    }
    public double calculateGreeceTax(double income) {
        if (income <= 10000) return income * 0.09;
        else if (income <= 20000) return 10000 * 0.09 + (income - 10000) * 0.22;
        else if (income <= 30000) return 10000 * 0.09 + 10000 * 0.22 + (income - 20000) * 0.28;
        else if (income <= 40000) return 10000 * 0.09 + 10000 * 0.22 + 10000 * 0.28 + (income - 30000) * 0.36;
        else return 10000 * 0.09 + 10000 * 0.22 + 10000 * 0.28 + 10000 * 0.36 + (income - 40000) * 0.44;
    }
    public double calculateTaxCyprus(double income) {
        if (income <= 19500) return 0;
        else if (income <= 28000) return (income - 19500) * 0.20;
        else if (income <= 36300) return 8500 * 0.20 + (income - 28000) * 0.25;
        else if (income <= 60000) return 8500 * 0.20 + 8300 * 0.25 + (income - 36300) * 0.30;
        else return 8500 * 0.20 + 8300 * 0.25 + 23700 * 0.30 + (income - 60000) * 0.35;
    }
    public double calculateTaxUSA(double income) {
        if (income <= 11000) return income * 0.10;
        else if (income <= 44725) return 11000 * 0.10 + (income - 11000) * 0.12;
        else if (income <= 95375) return 11000 * 0.10 + 33725 * 0.12 + (income - 44725) * 0.22;
        else return 11000 * 0.10 + 33725 * 0.12 + 50650 * 0.22 + (income - 95375) * 0.24;
    }
    public double calculateTaxUK(double income) {
        double taxFreeAllowance = 12570;
        if (income <= taxFreeAllowance) return 0;
        else if (income <= 50270) return (income - taxFreeAllowance) * 0.20;
        else if (income <= 125140) return (50270 - taxFreeAllowance) * 0.20 + (income - 50270) * 0.40;
        else return (50270 - taxFreeAllowance) * 0.20 + (125140 - 50270) * 0.40 + (income - 125140) * 0.45;
    }
    public double calculateTaxGermany(double income) {
        if (income <= 10908) return 0;
        else if (income <= 15999) return (income - 10908) * 0.14;
        else if (income <= 62809) return (income - 10908) * 0.30;
        else if (income <= 277825) return (income - 62809) * 0.42 + 51901 * 0.30;
        else return (income - 277825) * 0.45 + (277825 - 62809) * 0.42 + 51901 * 0.30;
    }
    public double convertFuel(double value, @NotNull String from, String to) {
        double lPer100km = switch (from) {
            case "L/100km" -> value;
            case "MPG (UK)" -> 282.5 / value;
            case "MPG (US)" -> 235.2 / value;
            default -> throw new IllegalArgumentException();
        };
        return switch (to) {
            case "L/100km" -> lPer100km;
            case "MPG (UK)" -> 282.5 / lPer100km;
            case "MPG (US)" -> 235.2 / lPer100km;
            default -> throw new IllegalArgumentException();
        };
    }
    public double calculateMonthlyPayment(double totalAmount, double time) {
        return totalAmount/(time * 12);
    }
    public double calculateTotalAmount(double principal, double rate, double time){
        return principal * Math.pow(1 + rate, time);
    }
    public double calculatePowerCost(double power, double hours, double rate){
        double energyCosumed = power*hours/100;
        double cost = energyCosumed * rate;
        return cost;
    }
    public BufferedImage generateQRImage(String text, int width, int height) throws WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
    public String getGreekDayName(@NotNull DayOfWeek day) {
        return switch (day) {
            case MONDAY -> "Δευτέρα";
            case TUESDAY -> "Τρίτη";
            case WEDNESDAY -> "Τετάρτη";
            case THURSDAY -> "Πέμπτη";
            case FRIDAY -> "Παρασκευή";
            case SATURDAY -> "Σάββατο";
            case SUNDAY -> "Κυριακή";
        };
    }
    public String getGreekMonthName(int month) {
        return switch (month) {
            case 1 -> "Ιανουαρίου";
            case 2 -> "Φεβρουαρίου";
            case 3 -> "Μαρτίου";
            case 4 -> "Απριλίου";
            case 5 -> "Μαΐου";
            case 6 -> "Ιουνίου";
            case 7 -> "Ιουλίου";
            case 8 -> "Αυγούστου";
            case 9 -> "Σεπτεμβρίου";
            case 10 -> "Οκτωβρίου";
            case 11 -> "Νοεμβρίου";
            case 12 -> "Δεκεμβρίου";
            default -> "";
        };
    }
    public double factorial(double n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }
    public double calcualteAdvnaced(@NotNull String operation, double input){
        double result = 0;
        switch(operation) {
            case "log":
                result = Math.log10(input);
                break;
            case "ln":
                result = Math.log(input);
                break;
            case "power":
                result = Math.pow(input, 2); // Προσαρμογή δύναμης
                break;
            case "factorial":
                result = factorial(input);
                break;
            case "sqrt":
                result = Math.sqrt(input);
                break;
        }

        return result;
    }
    public double calculate(String operation, double input, double input2) {
        double result = 0;
        try {
            switch (operation) {
                case "+":
                    result = input + input2;
                    break;
                case "-":
                    result = input - input2;
                    break;
                case "*":
                    result = input * input2;
                    break;
                case "/":
                    if (input2 != 0) {
                        result = input / input2;
                    }
                    break;
                case "%":
                    result = input % input2;
                    break;
            }

        } catch (Exception e) {
            showError();
        }
        return result;
    }
    public double calculateTrig(String function, double input) {
        double result = 0;
        try {
            switch (function) {
                case "sin":
                    result = Math.sin(Math.toRadians(input));
                    break;
                case "cos":
                    result = Math.cos(Math.toRadians(input));
                    break;
                case "tan":
                    result = Math.tan(Math.toRadians(input));
                    break;
                default:
                    return result;
            }
        } catch (Exception e) {
            showError();
        }
        return result;
    }
    public void showError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Σφάλμα");
        alert.setHeaderText(null);
        alert.setContentText("Σφάλμα στην εισαγωγή!");
        alert.showAndWait();
    }
    public double convertToPa(double value, @NotNull String unit) {
        return switch (unit) {
            case "Bar" -> value * BAR;
            case "Atm" -> value * ATM;
            case "Psi" -> value * PSI;
            default -> value;
        };
    }
    public double convertFromPa(double pa, @NotNull String unit) {
        return switch (unit) {
            case "Bar" -> pa / BAR;
            case "Atm" -> pa / ATM;
            case "Psi" -> pa / PSI;
            default -> pa;
        };
    }
    public void updateColor(Color color, @NotNull TextField hexField, @NotNull TextField rgbField, @NotNull TextField rgbaField, @NotNull Pane colorPane) {
        String hex = colorToHex(color);
        String rgb = colorToRGB(color);
        String rgba = colorToRGBA(color);
        hexField.setText(hex);
        rgbField.setText(rgb);
        rgbaField.setText(rgba);
        colorPane.setStyle("-fx-background-color: " + hex + ";");
    }
    public static void copyToClipboard(String text) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);
    }
    @NotNull
    public static String colorToHex(@NotNull Color color) {
        return String.format("#%02X%02X%02X",
                        (int)(color.getRed() * 255),
                        (int)(color.getGreen() * 255),
                        (int)(color.getBlue() * 255));
    }
    @NotNull
    public static String colorToRGB(@NotNull Color color) {
        return String.format("rgb(%d, %d, %d)",
                        (int)(color.getRed() * 255),
                        (int)(color.getGreen() * 255),
                        (int)(color.getBlue() * 255));
    }
    @NotNull
    public static String colorToRGBA(@NotNull Color color) {
        return String.format("rgba(%d, %d, %d, %.2f)",
                        (int)(color.getRed() * 255),
                        (int)(color.getGreen() * 255),
                        (int)(color.getBlue() * 255),
                        color.getOpacity());
    }
    public static void copyAll(String hex, String rgb, String rgba) {
        String fullText = "HEX: " + hex + "\nRGB: " + rgb + "\nRGBA: " + rgba;
        copyToClipboard(fullText);
    }
    public void toggleDarkMode(@NotNull Pane colorPane, boolean isDarkMode ) {
        colorPane.setStyle("-fx-background-color: " + (isDarkMode ? "#333333" : "#FFFFFF") + ";");
    }
    public void showCopiedLabel() {
        copiedLabel.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(e -> copiedLabel.setVisible(false));
        pause.play();
    }
    public double calculateSimpleInterest(double P, double R, double T) {
        return (P * R * T) / 100.0;
    }
    public double calculateCompoundInterest(double P,double R, double n, double T) {
        double amount = P * Math.pow(1 + R / n, n * T);
        double interest = amount - P;
        return interest;
    }
    public void handleAddSubtract(boolean isAdd) {
        LocalDate base = baseDatePicker.getValue();
        try {
            int days = Integer.parseInt(daysInputField.getText());
            if (base != null) {
                LocalDate result = isAdd ? base.plusDays(days) : base.minusDays(days);
                resultDateLabel.setText("Αποτέλεσμα: " + result);
            }
        } catch (NumberFormatException e) {
            resultDateLabel.setText("Μη έγκυρος αριθμός ημερών.");
        }
    }
}
