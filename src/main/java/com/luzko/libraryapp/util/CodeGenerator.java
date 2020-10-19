package com.luzko.libraryapp.util;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

/**
 * The type Code generator.
 */
public final class CodeGenerator {
    private CodeGenerator() {

    }

    /**
     * Generate unique code for registration.
     *
     * @return the string
     */
    public static String generate() {
        UUID confirmCodeUuid = Generators.randomBasedGenerator().generate();
        return confirmCodeUuid.toString().replaceAll("-", "");
    }
}
