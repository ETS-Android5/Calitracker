package com.cuyer.calitracker.Controller;

public class BMRCalcUtil {

    public static final BMRCalcUtil instance = new BMRCalcUtil();


    public static BMRCalcUtil getInstance() {
        return instance;
    }

    public double calculateBMRMetricMale(double heightCm, double weightKg, int age) {
        return (10 * weightKg) + (6.25 * heightCm) - (5 * age) + 5;
    }

    public double calculateBMRMetricFemale(double heightCm, double weightKg, int age) {
        return (10 * weightKg) + (6.25 * heightCm) - (5 * age) - 161;
    }
}
