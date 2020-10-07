package com.luzko.libraryapp.validator;

import com.luzko.libraryapp.model.dao.ColumnName;

import java.util.Map;

public class UserValidator {
    private static final String EMPTY_VALUE = "";
    private static final String LOGIN_PATTERN = "^[\\p{Alnum}._-]{5,20}$";
    private static final String PASSWORD_PATTERN = "(?=.*[a-z])(?=.*\\d)([A-Za-z\\d]{8,20})";
    private static final String NAME_PATTERN = "^([А-Я]{1}[а-я]{3,20}|[A-Z]{1}[a-z]{3,20})$";
    private static final String EMAIL_PATTERN = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";

    public boolean isValidRegistrationParameters(Map<String, String> registrationParameters) {
        boolean isValidParameters = true;
        if (!isLoginValid(registrationParameters.get(ColumnName.LOGIN))) {
            isValidParameters = false;
            registrationParameters.put(ColumnName.LOGIN, EMPTY_VALUE);
        }
        if (!isPasswordValid(registrationParameters.get(ColumnName.PASSWORD))) {
            isValidParameters = false;
            registrationParameters.put(ColumnName.PASSWORD, EMPTY_VALUE);
        }
        if (!isNameValid(registrationParameters.get(ColumnName.NAME))) {
            isValidParameters = false;
            registrationParameters.put(ColumnName.NAME, EMPTY_VALUE);
        }
        if (!isNameValid(registrationParameters.get(ColumnName.SURNAME))) {
            isValidParameters = false;
            registrationParameters.put(ColumnName.SURNAME, EMPTY_VALUE);
        }

        if (!isEmailValid(registrationParameters.get(ColumnName.EMAIL))) {
            isValidParameters = false;
            registrationParameters.put(ColumnName.EMAIL, EMPTY_VALUE);
        }
        return isValidParameters;
    }

    public boolean isLoginValid(String login) {
        boolean isLoginCorrect = false;
        if (login != null && !login.isEmpty()) {
            isLoginCorrect = login.matches(LOGIN_PATTERN);
        }

        return isLoginCorrect;
    }

    public boolean isPasswordValid(String password) {
        boolean isPasswordsCorrect = false;
        if (password != null && !password.isEmpty()) {
            isPasswordsCorrect = password.matches(PASSWORD_PATTERN);
        }

        return isPasswordsCorrect;
    }

    public boolean isNameValid(String name) {
        boolean isNameCorrect = false;
        if (name != null && !name.isEmpty()) {
            isNameCorrect = name.matches(NAME_PATTERN);
        }

        return isNameCorrect;
    }

    public boolean isEmailValid(String email) {
        boolean isEmailCorrect = false;
        if (email != null && !email.isEmpty()) {
            isEmailCorrect = email.matches(EMAIL_PATTERN);
        }

        return isEmailCorrect;
    }
}
