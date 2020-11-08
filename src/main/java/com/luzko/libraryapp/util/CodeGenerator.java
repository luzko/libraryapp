package com.luzko.libraryapp.util;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

/**
 * The type represents the code generator.
 */
public final class CodeGenerator {
    private CodeGenerator() {

    }

    /**
     * Generate unique code for registration.
     *
     * @return the code confirm
     */
    public static String generateCodeConfirm() {
        return generate().substring(0, 10);
    }

    /**
     * Generate unique code.
     *
     * @return the unique code
     */
    public static String generate() {
        UUID confirmCodeUuid = Generators.randomBasedGenerator().generate();
        return confirmCodeUuid.toString().replaceAll("-", "");
    }
}
