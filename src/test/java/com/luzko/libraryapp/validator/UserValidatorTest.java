package com.luzko.libraryapp.validator;

import com.luzko.libraryapp.model.dao.ColumnName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UserValidatorTest {
    private Map<String, String> validRegisterParameter;
    private Map<String, String> invalidRegisterParameter;

    @BeforeClass
    public void setUp() {
        validRegisterParameter = new HashMap<>();
        validRegisterParameter.put(ColumnName.LOGIN, "user24");
        validRegisterParameter.put(ColumnName.PASSWORD, "sdfDFGh123s");
        validRegisterParameter.put(ColumnName.NAME, "Dmitry");
        validRegisterParameter.put(ColumnName.SURNAME, "Tarasuk");
        validRegisterParameter.put(ColumnName.EMAIL, "dmitry.tarasuk@gmail.com");

        invalidRegisterParameter = new HashMap<>();
        invalidRegisterParameter.put(ColumnName.LOGIN, "user24");
        invalidRegisterParameter.put(ColumnName.PASSWORD, "s23s");
        invalidRegisterParameter.put(ColumnName.NAME, "Dmitry");
        invalidRegisterParameter.put(ColumnName.SURNAME, "Tarasuk");
        invalidRegisterParameter.put(ColumnName.EMAIL, "dmitry.tarasuk@@@gmail.com");
    }

    @Test
    public void isValidBookParameterPositiveTest() {
        boolean actual = UserValidator.isValidRegistrationParameter(validRegisterParameter);
        assertTrue(actual);
    }

    @Test
    public void isValidBookParameterNegativeTest() {
        boolean actual = UserValidator.isValidRegistrationParameter(invalidRegisterParameter);
        assertFalse(actual);
    }
}
