package com.luzko.libraryapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final String DATE_PATTERN = "dd-MM-yyyy";

    public static void defineDate(long asd) {
        Date date = new Date(asd);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        String aa = dateFormat.format(date);


        System.out.println(aa);
    }

    public static long defineMillisecondsFromNowDate() {
        Date date = new Date();
        return date.getTime();
    }

    public static void main(String[] args) {
        //System.out.println(defineMillisecondsFromNowDate());
        long a = 1602598546577L;
        defineDate(a);
    }
}
