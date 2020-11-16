package com.luzko.libraryapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type represents the validator for values.
 */
public final class ValueValidator {
    private static final String XSS_PATTERN = "(\\b)(on\\S+)(\\s*)=|javascript:|(<\\s*)(\\/*)script|style(\\s*)=|(<\\s*)meta";
    private static final String CONFIRM_CODE_PATTERN = "^[\\w]{5,20}$";
    private static final String SUBJECT_PATTERN = "^[\\p{L}\\d ?!,.']{5,35}$";
    private static final String MESSAGE_PATTERN = "^[\\p{L}\\d ?!,.']{5,300}$";

    private ValueValidator() {

    }

    /**
     * Check valid confirm code.
     *
     * @param value the confirm code value
     * @return the boolean
     */
    public static boolean isValidConfirmCode(String value) {
        return isValidValue(value) && value.matches(CONFIRM_CODE_PATTERN);
    }

    /**
     * Check valid message.
     *
     * @param value the message value
     * @return the boolean
     */
    public static boolean isValidMessage(String value) {
        return isValidValue(value) && value.matches(MESSAGE_PATTERN);
    }

    /**
     * Check valid subject.
     *
     * @param value the subject value
     * @return the boolean
     */
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
        Pattern pattern = Pattern.compile(XSS_PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
