package com.rentmaintainance.app.utils;

public class FloatUtil {
    public static float tryParse(String value, float defaultValue) {
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}