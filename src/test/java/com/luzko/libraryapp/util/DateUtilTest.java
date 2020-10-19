package com.luzko.libraryapp.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class DateUtilTest {
    @Test
    public void defineDateValuePositiveTest() {
        long inputValue = 1603097878313L;
        String actual = DateUtil.defineDateValue(inputValue);
        String expected = "19-10-2020";
        assertEquals(actual, expected);
    }

    @Test
    public void defineDateValueNegativeTest() {
        long inputValue = 1603097878313L;
        String actual = DateUtil.defineDateValue(inputValue);
        String expected = "20-11-2019";
        assertNotEquals(actual, expected);
    }
}
