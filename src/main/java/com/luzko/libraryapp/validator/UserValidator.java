package com.luzko.libraryapp.validator;

import com.luzko.libraryapp.model.dao.ColumnName;

import java.util.Map;

public final class UserValidator {
    private static final String EMPTY_VALUE = "";
    private static final String LOGIN_PATTERN = "^[\\w.]{5,20}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[\\p{Lower}])(?=.*[\\p{Upper}])(?=.*\\d)[\\p{Alnum}]{6,20}$";
    private static final String NAME_PATTERN = "^[\\p{L}]{3,25}$";
    private static final String EMAIL_PATTERN = "^[\\w.+-]{3,30}@[\\w.-]{2,15}\\.[\\p{Lower}]{2,4}$";

    private UserValidator() {

    }

    public static boolean isValidRegistrationParameter(Map<String, String> registrationParameter) {
        boolean isValidParameter = true;
        if (!isLoginValid(registrationParameter.get(ColumnName.LOGIN))) {
            isValidParameter = false;
            registrationParameter.put(ColumnName.LOGIN, EMPTY_VALUE);
        }
        if (!isPasswordValid(registrationParameter.get(ColumnName.PASSWORD))) {
            isValidParameter = false;
            registrationParameter.put(ColumnName.PASSWORD, EMPTY_VALUE);
        }
        if (!isNameValid(registrationParameter.get(ColumnName.NAME))) {
            isValidParameter = false;
            registrationParameter.put(ColumnName.NAME, EMPTY_VALUE);
        }
        if (!isNameValid(registrationParameter.get(ColumnName.SURNAME))) {
            isValidParameter = false;
            registrationParameter.put(ColumnName.SURNAME, EMPTY_VALUE);
        }

        if (!isEmailValid(registrationParameter.get(ColumnName.EMAIL))) {
            isValidParameter = false;
            registrationParameter.put(ColumnName.EMAIL, EMPTY_VALUE);
        }
        return isValidParameter;
    }

    public static boolean isLoginValid(String login) {
        boolean isLoginCorrect = false;
        if (login != null && !login.isEmpty()) {
            isLoginCorrect = login.matches(LOGIN_PATTERN);
        }
        return isLoginCorrect;
    }

    public static boolean isPasswordValid(String password) {
        boolean isPasswordsCorrect = false;
        if (password != null && !password.isEmpty()) {
            isPasswordsCorrect = password.matches(PASSWORD_PATTERN);
        }
        return isPasswordsCorrect;
    }

    public static boolean isNameValid(String name) {
        boolean isNameCorrect = false;
        if (name != null && !name.isEmpty()) {
            isNameCorrect = name.matches(NAME_PATTERN);
        }
        return isNameCorrect;
    }

    public static boolean isEmailValid(String email) {
        boolean isEmailCorrect = false;
        if (email != null && !email.isEmpty()) {
            isEmailCorrect = email.matches(EMAIL_PATTERN);
        }
        return isEmailCorrect;
    }
}
