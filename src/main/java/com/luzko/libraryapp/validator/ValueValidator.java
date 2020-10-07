package com.luzko.libraryapp.validator;

public class ValueValidator {
    private static final String XSS_PATTERN = "(?i)<script.*?>.*?</script.*?>";

    public boolean isValidValue(String value) {
        return value != null && !value.isBlank() && !isXssAttack(value);
    }

    private boolean isXssAttack(String value) {
        return value.matches(XSS_PATTERN);
    }
}
