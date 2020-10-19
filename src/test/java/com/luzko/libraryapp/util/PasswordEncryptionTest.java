package com.luzko.libraryapp.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class PasswordEncryptionTest {
    @Test
    public void encryptPositiveTest() {
        String inputValue = "asdfASDF1234";
        String actual = PasswordEncryption.encrypt(inputValue);
        String expected = "adc0bbac0ace2ce1580955d519d5c42eef9c5ca1";
        assertEquals(actual, expected);
    }

    @Test
    public void encryptNegativeTest() {
        String inputValue = "asda23gadf";
        String actual = PasswordEncryption.encrypt(inputValue);
        String expected = "asdqed345lsafdasdf";
        assertNotEquals(actual, expected);
    }
}
