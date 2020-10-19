package com.luzko.libraryapp.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CodeGeneratorTest {
    @Test
    public void generatePositiveTest() {
        String actual = CodeGenerator.generate();
        assertEquals(actual.length(), 32);
    }

    @Test
    public void generateNegativeTest() {
        String actual = CodeGenerator.generate();
        assertNotEquals(actual.length(), 25);
    }
}
