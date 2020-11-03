package com.luzko.libraryapp.model.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ValueValidatorTest {
    @Test
    public void isValidValuePositiveTest() {
        String inputValue = "Hello, world!";
        boolean actual = ValueValidator.isValidValue(inputValue);
        assertTrue(actual);
    }

    @Test
    public void isValidValueNegativeTest() {
        String inputValue = null;
        boolean actual = ValueValidator.isValidValue(inputValue);
        assertFalse(actual);
    }
}
