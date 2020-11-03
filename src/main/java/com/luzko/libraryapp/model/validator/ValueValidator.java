package com.luzko.libraryapp.model.validator;

/**
 * The type Value validator.
 */
public final class ValueValidator {
    private static final String XSS_PATTERN = "(?i)<script.*?>.*?</script.*?>";
    private static final String CONFIRM_CODE_PATTERN = "^[\\p{L} ]{5,20}$";
    private static final String SUBJECT_PATTERN = "^[\\p{L} ]{5,35}$";
    private static final String MESSAGE_PATTERN = "^[\\p{L} ]{5,300}$";

    private ValueValidator() {

    }

    public static boolean isValidConfirmCode(String value) {
        return isValidValue(value) && value.matches(CONFIRM_CODE_PATTERN);
    }

    public static boolean isValidMessage(String value) {
        return isValidValue(value) && value.matches(MESSAGE_PATTERN);
    }

    public static boolean isValidSubject(String value) {
        return isValidValue(value) && value.matches(SUBJECT_PATTERN);
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
