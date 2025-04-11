package com.droidgeniuslabs.tooltip.Util;

public class Utilities {
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
}
