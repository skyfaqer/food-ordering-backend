package com.cgy.sell.utils;

public class MathUtil {

    private static final Double EQUAL_PRECISION = 0.01;

    // Check if two amounts are equal
    public static Boolean equals(Double d1, Double d2) {
        return Math.abs(d1 - d2) < EQUAL_PRECISION;
    }
}
