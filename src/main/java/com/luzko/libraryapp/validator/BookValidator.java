package com.luzko.libraryapp.validator;

public class BookValidator {
    private static final String NAME_PATTERN = "^[\\p{L}]{3,25}$";

    public boolean isValidAuthorName(String name) {
        boolean isNameCorrect = false;
        if (name != null && !name.isEmpty()) {
            isNameCorrect = name.matches(NAME_PATTERN);
        }
        return isNameCorrect;
    }
}
