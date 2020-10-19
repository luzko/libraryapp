package com.luzko.libraryapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Date util.
 */
public final class DateUtil {
    private static final String DATE_PATTERN = "dd-MM-yyyy";

    private DateUtil() {

    }

    /**
     * Define date value by milliseconds.
     *
     * @param milliseconds the milliseconds
     * @return the string
     */
    public static String defineDateValue(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        return dateFormat.format(date);
    }

    /**
     * Define count milliseconds from date now.
     *
     * @return the long
     */
    public static long defineCountMillisecondsFromNow() {
        Date date = new Date();
        return date.getTime();
    }
}
