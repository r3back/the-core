package com.qualityplus.assistant.util.math;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public final class MathUtils {
    private static final DecimalFormat ROUND_COMMA_FORMAT = new DecimalFormat("#,###");
    private static final DecimalFormat ROUND_DOT_FORMAT = new DecimalFormat("#.##");
    private final static TreeMap<Integer, String> FORMAT = new TreeMap<>();
    private static final double[] SIN_FORMAT = new double[65536];
    private static final double[] PI = new double[65536];

    static {
        //roundFormat.setGroupingSize(3);

        FORMAT.put(1000, "M");
        FORMAT.put(900, "CM");
        FORMAT.put(500, "D");
        FORMAT.put(400, "CD");
        FORMAT.put(100, "C");
        FORMAT.put(90, "XC");
        FORMAT.put(50, "L");
        FORMAT.put(40, "XL");
        FORMAT.put(10, "X");
        FORMAT.put(9, "IX");
        FORMAT.put(5, "V");
        FORMAT.put(4, "IV");
        FORMAT.put(1, "I");
        FORMAT.put(0, "0");
    }

    public static double getPercentage(final double number, final double percentage){
        return (percentage * number) / 100;
    }

    public static double sinCalc(final double a) {
        float f = (float) a;
        return SIN_FORMAT[(int) (f * 10430.378F) & '\uffff'];
    }

    public static String toRoman(int number) {
        int l =  FORMAT.floorKey(number);
        if (number == l)
            return FORMAT.get(number);
        return FORMAT.get(l) + toRoman(number-l);
    }

    public static String toInt(double value){
        return String.valueOf((int) value);
    }

    public static String round(double value){
        if(value <= 999)
            return ROUND_DOT_FORMAT.format(value);
        else
            return ROUND_COMMA_FORMAT.format(value);
    }

    public int randomIntBetween(final int min, final int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public int randomUpTo(final int max) {
        return randomIntBetween(0, max);
    }

    public double randomBetween(final double min, final double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }



    public static double offset(Location paramLocation1, Location paramLocation2) {
        return offset(paramLocation1.toVector(), paramLocation2.toVector());
    }

    public static double offset(Vector paramVector1, Vector paramVector2) {
        return paramVector1.subtract(paramVector2).length();
    }

    public static Integer intOrNull(String toTraslate){
        try {
            return Integer.parseInt(toTraslate);
        }catch (NumberFormatException e){
            return null;
        }
    }

    public static Integer intOrZero(String toTraslate){
        try {
            return Integer.parseInt(toTraslate);
        }catch (NumberFormatException e){
            return 0;
        }
    }

    public static Double doubleOrNull(String toTraslate){
        try {
            return Double.parseDouble(toTraslate);
        }catch (NumberFormatException e){
            return null;
        }
    }

    public static Double doubleOrZero(String toTraslate){
        try {
            return Double.parseDouble(toTraslate);
        }catch (NumberFormatException e){
            return 0D;
        }
    }

    public static int extractNumbers(String from){
        return intOrZero(from.replaceAll("[^0-9]", ""));
    }
}
