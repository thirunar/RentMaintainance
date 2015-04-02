package com.rentmaintainance.app.utils;

public class IntegerUtil {
    public static int tryParse(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}