package com.luzko.libraryapp.validator;

import com.luzko.libraryapp.model.dao.ColumnName;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BookValidatorTest {
    private Map<String, String> validBookParameter;
    private Map<String, String> invalidBookParameter;

    @BeforeClass
    public void setUp() {
        validBookParameter = new HashMap<>();
        validBookParameter.put(ColumnName.TITLE, "My new book");
        validBookParameter.put(ColumnName.YEAR, "1996");
        validBookParameter.put(ColumnName.PAGES, "312");
        validBookParameter.put(ColumnName.NUMBER_COPIES, "4");
        validBookParameter.put(ColumnName.DESCRIPTION, "Bla bla bla");

        invalidBookParameter = new HashMap<>();
        invalidBookParameter.put(ColumnName.TITLE, "My new book.");
        invalidBookParameter.put(ColumnName.YEAR, "-233");
        invalidBookParameter.put(ColumnName.PAGES, "111111");
        invalidBookParameter.put(ColumnName.NUMBER_COPIES, "-5");
        invalidBookParameter.put(ColumnName.DESCRIPTION, "Bla bla bla");
    }

    @Test
    public void isValidBookParameterPositiveTest() {
        boolean actual = BookValidator.isValidBookParameter(validBookParameter);
        assertTrue(actual);
    }

    @Test
    public void isValidBookParameterNegativeTest() {
        boolean actual = BookValidator.isValidBookParameter(invalidBookParameter);
        assertFalse(actual);
    }

    @Test
    public void isValidTitlePositiveTest() {
        String inputValue = "Дети подземелья";
        boolean actual = BookValidator.isValidTitle(inputValue);
        assertTrue(actual);
    }

    @Test
    public void isValidTitleNegativeTest() {
        String inputValue = "/.12 asd";
        boolean actual = BookValidator.isValidTitle(inputValue);
        assertFalse(actual);
    }

    @Test
    public void isYearValidPositiveTest() {
        int inputValue = 1999;
        boolean actual = BookValidator.isYearValid(inputValue);
        assertTrue(actual);
    }

    @Test
    public void isYearValidNegativeTest() {
        int inputValue = 3000;
        boolean actual = BookValidator.isYearValid(inputValue);
        assertFalse(actual);
    }

    @Test
    public void isPagesValidPositiveTest() {
        int inputValue = 100;
        boolean actual = BookValidator.isPagesValid(inputValue);
        assertTrue(actual);
    }

    @Test
    public void inputValueNegativeTest() {
        int inputValue = -5;
        boolean actual = BookValidator.isPagesValid(inputValue);
        assertFalse(actual);
    }

    @AfterClass
    public void tierDown() {
        validBookParameter = null;
        invalidBookParameter = null;
    }
}
