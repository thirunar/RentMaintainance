package com.rentmaintainance.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String formatDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyy", Locale.getDefault());

        return dateFormat.format(date);
    }

    public static Date getDateTime(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyy", Locale.getDefault());
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
