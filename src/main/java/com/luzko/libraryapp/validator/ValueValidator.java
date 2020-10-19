package com.luzko.libraryapp.validator;

/**
 * The type Value validator.
 */
public final class ValueValidator {
    private static final String XSS_PATTERN = "(?i)<script.*?>.*?</script.*?>";

    private ValueValidator() {

    }

    /**
     * Check valid value.
     *
     * @param value the value
     * @return the boolean
     */
    public static boolean isValidValue(String value) {
        return value != null && !value.isBlank() && !isXssAttack(value);
    }

    private static boolean isXssAttack(String value) {
        return value.matches(XSS_PATTERN);
    }
}
