package com.luzko.libraryapp.util;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class ConfirmCodeGenerator {

    private static final ConfirmCodeGenerator instance = new ConfirmCodeGenerator();

    public static ConfirmCodeGenerator getInstance() {
        return instance;
    }

    private ConfirmCodeGenerator() {

    }

    public String generate() {
        UUID confirmCodeUuid = Generators.randomBasedGenerator().generate();
        return confirmCodeUuid.toString();
    }
}
