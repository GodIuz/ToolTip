package com.droidgeniuslabs.tooltip.Util;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utilities {
    private final String API_KEY = "acecfc556779dc60b6992973";
    private final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";
    public double convertToBytes(double value, String unit) {
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
    public double convertFromBytes(double value, String unit) {
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
    public int convertToDecimal(String input, String base) {
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
    public String convertFromDecimal(int decimalValue, String base) {
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
    public double toCelsius(double temp, String from) {
        return switch (from) {
            case "Celsius" -> temp;
            case "Fahrenheit" -> (temp - 32) * 5 / 9;
            case "Kelvin" -> temp - 273.15;
            case "Rankine" -> (temp - 491.67) * 5 / 9;
            case "Reaumur" -> temp * 1.25;
            default -> throw new IllegalArgumentException("Unknown unit: " + from);
        };
    }
    public double fromCelsius(double temp, String to) {
        return switch (to) {
            case "Celsius" -> temp;
            case "Fahrenheit" -> (temp * 9 / 5) + 32;
            case "Kelvin" -> temp + 273.15;
            case "Rankine" -> (temp + 273.15) * 9 / 5;
            case "Reaumur" -> temp * 0.8;
            default -> throw new IllegalArgumentException("Unknown unit: " + to);
        };
    }
    public double convertToMetersPerSecond(double value, String unit) {
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
    public double convertFromMetersPerSecond(double value, String unit) {
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
    public double fromAreaConvert(double value, String unit) {
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
    public double toAreaConvert(double value, String unit) {
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
    public double fromTimeConvert(double value, String unit) {
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
    public double toTimeConvert(double value, String unit) {
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
    public double fromDistanceConvert(double value, String unit) {
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
    public double toDistanceConvert(double value, String unit) {
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
    public double fromMassConvert(double value, String unit){
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
    public double toMassConvert(double value, String unit){
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
    public double toLiters(String unit, double value) {
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

    public double fromLiters(String unit, double value) {
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
}
