package com.qualityplus.assistant.util.math;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public final class MathUtils {
    private static final DecimalFormat roundCommaFormat2 = new DecimalFormat("#,###");
    private static final DecimalFormat roundDotFormat = new DecimalFormat("#.##");
    private final static TreeMap<Integer, String> map = new TreeMap<>();

    static {
        //roundFormat.setGroupingSize(3);

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
        map.put(0, "0");
    }

    public static String toRoman(int number) {
        int l =  map.floorKey(number);
        if (number == l)
            return map.get(number);
        return map.get(l) + toRoman(number-l);
    }

    public static String toInt(double value){
        return String.valueOf((int) value);
    }

    public static String round(double value){
        if(value <= 999)
            return roundDotFormat.format(value);
        else
            return roundCommaFormat2.format(value);
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
