package com.luzko.libraryapp.controller;

public class AttributeName {
    private AttributeName() {

    }

    //general
    public static final String LOCALE = "locale";
    public static final String ENCODING = "encoding";
    public static final String RUS = "ru_RU";
    public static final String ENG = "en_US";


    //error
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String ERROR_LOGIN_PASSWORD_MESSAGE = "errorLoginPasswordMessage";
    public static final String ERROR_DATA_MESSAGE = "errorDataMessage";
    public static final String PARAM_CONFIRM_ERROR = "confirmError";
    public static final String ERROR_APPROVE = "approveError";
    public static final String NOT_FOUND_ORDERS = "notFoundOrders";
    public static final String AVATAR_ERROR = "errorAvatar";

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
    public static final String PATH_ORDER_SUCCESS = "order.success";
    public static final String PATH_ORDER_ERROR = "order.error";
    public static final String PATH_NOT_APPROVE_USER = "order.approve.error";
    public static final String PATH_ORDER_NOT_FOUND = "order.not.found";
    public static final String PATH_AVATAR_CHANGES = "avatar.error";

    //user
    public static final String ALL_USERS = "allUsers";
    public static final String LOGIN = "login";
    public static final String USER_STATUS = "status";
    public static final String EMAIL = "email";






    public static final String USER_NAME = "userName";
    public static final String USER_SURNAME = "userSurname";
    public static final String USER_ROLE = "userRole";

    public static final String REGISTRATION_PARAMETER = "registrationParameters";
    public static final String AVATAR = "avatar";

    //profile
    public static final String TYPE_PROFILE_PAGE = "type";
    public static final String SEE_PROFILE_PAGE = "see";
    public static final String CHANGE_PROFILE_PAGE = "change";
    public static final String CHANGE_SAVED = "ChangedSave";
    public static final String LOGIN_ERROR = "loginError";
    public static final String NAME_ERROR = "usernameError";
    public static final String SURNAME_ERROR = "surnameError";

    //view



    //book
    public static final String ALL_BOOKS = "allBooks";
    public static final String BOOK = "book";

    public static final String OVERVIEW_ERROR = "overviewError";
    public static final String ALL_CATEGORIES = "allCategories";
    public static final String BOOK_PARAMETER = "bookParameter";
    public static final String CORRECT_BOOK_MESSAGE = "createBookSuccess";

    //message


    //author
    public static final String ALL_AUTHORS = "allAuthors";
    public static final String CREATE_TYPE = "createType";



    public static final String CORRECT_DATA_MESSAGE = "createAuthorSuccess";


    //order
    public static final String USER_ID = "userId";
    public static final String ORDER_TYPE = "orderType";
    public static final String TYPE_HOME = "home";
    public static final String USER_ORDER = "user";
    public static final String BOOK_ORDER = "book";
    public static final String NEW_ORDER = "new";
    public static final String ALL_ORDER = "all";
    public static final String ALL_ORDERS = "allOrders";

    public static final String ORDER_ERROR = "orderError";
    public static final String ORDER_SUCCESS = "orderSuccess";

    //pagination
    public static final String COUNT_PAGE = "countPage";
    public static final String CURRENT_PAGE = "currentPage";

    //message

    public static final String ACTIVE = "active";
    public static final String BLOCKED = "blocked";

    //upload
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_VALUE = "inline; filename=\"%s\"";
}
