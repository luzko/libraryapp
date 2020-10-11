package com.luzko.libraryapp.factory;

import com.luzko.libraryapp.validator.BookValidator;
import com.luzko.libraryapp.validator.UserValidator;
import com.luzko.libraryapp.validator.ValueValidator;

public class ValidatorFactory {
    private final UserValidator userValidator;
    private final ValueValidator valueValidator;
    private final BookValidator bookValidator;

    private ValidatorFactory() {
        this.userValidator = new UserValidator();
        this.valueValidator = new ValueValidator();
        this.bookValidator = new BookValidator();
    }

    private static class ValidatorFactorySingletonHolder {
        static final ValidatorFactory INSTANCE = new ValidatorFactory();
    }

    public static ValidatorFactory getInstance() {
        return ValidatorFactory.ValidatorFactorySingletonHolder.INSTANCE;
    }

    public UserValidator getUserValidator() {
        return userValidator;
    }

    public ValueValidator getValueValidator() {
        return valueValidator;
    }

    public BookValidator getBookValidator() {
        return bookValidator;
    }
}
