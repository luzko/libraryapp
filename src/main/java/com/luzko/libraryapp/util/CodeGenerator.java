package com.luzko.libraryapp.util;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

public final class CodeGenerator {
    private CodeGenerator() {

    }

    public static String generate() {
        UUID confirmCodeUuid = Generators.randomBasedGenerator().generate();
        return confirmCodeUuid.toString().replaceAll("-", "");
    }
}