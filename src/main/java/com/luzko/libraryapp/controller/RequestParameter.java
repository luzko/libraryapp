package com.luzko.libraryapp.controller;

public class RequestParameter {
    private RequestParameter() {

    }

    //general
    public static final String EMPTY = "";
    public static final String COMMAND_NAME = "command";
    public static final String LOCALE = "locale";
    public static final String ENCODING = "encoding";
    public static final String RUS = "ru_RU";
    public static final String ENG = "en_US";
    public static final String CODE = "code";

    //error
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String ERROR_LOGIN_PASSWORD_MESSAGE = "errorLoginPasswordMessage";
    public static final String ERROR_DATA_MESSAGE = "errorDataMessage";
    public static final String PARAM_CONFIRM_ERROR = "confirmError";

    //message error
    public static final String PATH_LOGIN_EXIST = "message.login.exist";
    public static final String PATH_INCORRECT_DATA = "message.login.incorrect.data";
    public static final String PATH_INCORRECT_USER = "message.user.incorrect";
    public static final String PATH_LOGIN_ERROR = "message.login.error";
    public static final String PATH_INCORRECT_CODE = "message.confirm.code";
    public static final String PATH_SAVE_CHANGES = "message.changed.save";
    public static final String PATH_SURNAME_CHANGES = "message.user.surname.error";
    public static final String PATH_NAME_CHANGES = "message.user.name.error";
    public static final String PATH_LOGIN_CHANGES = "message.user.login.error";
    public static final String PATH_STATUS_CHANGES = "message.user.status.error";
    public static final String PATH_BOOK_OVERVIEW = "message.book.overview";
    public static final String PATH_AUTHOR_DATA = "message.author.data";
    public static final String PATH_AUTHOR_CORRECT = "message.author.correct";
    public static final String PATH_BOOK_CORRECT = "message.book.correct";
    public static final String PATH_BOOK_DATA = "message.book.data";

    //user
    public static final String USER_ID = "userId";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String USER_NAME = "userName";
    public static final String USER_SURNAME = "userSurname";
    public static final String USER_ROLE = "userRole";
    public static final String USER_STATUS = "status";
    public static final String REGISTRATION_PARAMETER = "registrationParameters";

    //profile
    public static final String TYPE_PROFILE_PAGE = "type";
    public static final String SEE_PROFILE_PAGE = "see";
    public static final String CHANGE_PROFILE_PAGE = "change";
    public static final String CHANGE_SAVED = "ChangedSave";
    public static final String LOGIN_ERROR = "loginError";
    public static final String NAME_ERROR = "usernameError";
    public static final String SURNAME_ERROR = "surnameError";

    //view
    public static final String ALL_USERS = "allUsers";
    public static final String ALL_BOOKS = "allBooks";

    //book
    public static final String BOOK = "book";
    public static final String BOOK_ID = "book_id";
    public static final String OVERVIEW_ERROR = "overviewError";
    public static final String TYPE_OVERVIEW_PAGE = "typeOverview";
    public static final String SEE_OVERVIEW_PAGE = "seeOverview";
    public static final String ALL_CATEGORIES = "allCategories";
    public static final String BOOK_PARAMETER = "bookParameter";
    public static final String CORRECT_BOOK_MESSAGE = "createBookSuccess";

    //message
    public static final String SUBJECT = "subject";
    public static final String MESSAGE = "message";

    //author
    public static final String AUTHOR_NAME = "authorName";
    public static final String CREATE_TYPE = "createType";
    public static final String CORRECT_DATA_MESSAGE = "createAuthorSuccess";
    public static final String ALL_AUTHORS = "allAuthors";

    //order
    public static final String ORDER_TYPE = "orderType";
    public static final String USER_ORDERS = "userOrders";
    public static final String BOOK_ORDERS = "bookOrders";
}