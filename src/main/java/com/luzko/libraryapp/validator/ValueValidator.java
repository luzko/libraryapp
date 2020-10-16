package com.luzko.libraryapp.validator;

public final class ValueValidator {
    private static final String XSS_PATTERN = "(?i)<script.*?>.*?</script.*?>";

    private ValueValidator() {

    }

    public static boolean isValidValue(String value) {
        return value != null && !value.isBlank() && !isXssAttack(value);
    }

    private static boolean isXssAttack(String value) {
        return value.matches(XSS_PATTERN);
    }
}
