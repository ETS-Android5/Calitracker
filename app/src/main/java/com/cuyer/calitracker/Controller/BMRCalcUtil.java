package com.cuyer.calitracker.Controller;

public class BMRCalcUtil {

    public static final BMRCalcUtil instance = new BMRCalcUtil();

    private static final int CENTIMETERS_IN_METER = 100;
    private static final int INCHES_IN_FOOT = 12;
    private static final int BMI_IMPERIAL_WEIGHT_SCALAR = 703;


    public static BMRCalcUtil getInstance() {
        return instance;
    }

    public double calculateBMRMetricMale(double heightCm, double weightKg, int age) {
        return (10 * weightKg) + (6.25 * heightCm) - (5 * age) + 5;
    }

    public double calculateBMRMetricFemale(double heightCm, double weightKg, int age) {
        return (10 * weightKg) + (6.25 * heightCm) - (5 * age) - 161;
    }

    public double calculateBMRImperialMale(double heightFeet, double heightInches, double weightLbs, int age) {
        double totalHeightInInches = (heightFeet * INCHES_IN_FOOT) + heightInches;
        return 66.47 + (6.24 * weightLbs) + (12.7 * totalHeightInInches) - (6.755 * age);
    }

    public double calculateBMRImperialFemale(double heightFeet, double heightInches, double weightLbs, int age) {
        double totalHeightInInches = (heightFeet * INCHES_IN_FOOT) + heightInches;
        return 655.1 + (4.35 * weightLbs) + (4.7 * totalHeightInInches) - (4.7 * age);
    }

}
