package com.luzko.libraryapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final String DATE_PATTERN = "dd-MM-yyyy";

    public static String defineDateValue(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        return dateFormat.format(date);
    }

    public static long defineCountMillisecondsFromNow() {
        Date date = new Date();
        return date.getTime();
    }
}
