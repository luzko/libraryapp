package com.luzko.libraryapp.util;

public class IdGenerator {
    private static final long MIN_ID = 1;
    private static final long MAX_ID = 100_000_000;
    private static long counter;

    private IdGenerator() {

    }

    public static long generateId() {
        counter++;
        if (MIN_ID + counter >= MAX_ID) {
            counter = 0;
        }
        return counter;
    }
}
