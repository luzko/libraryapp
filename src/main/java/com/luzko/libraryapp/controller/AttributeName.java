package com.luzko.libraryapp.controller;

public class AttributeName {
    private AttributeName() {

    }

    //general
    public static final String LOCALE = "locale";
    public static final String ENCODING = "encoding";

    //error
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String ERROR_LOGIN_PASSWORD_MESSAGE = "errorLoginPasswordMessage";
    public static final String ERROR_DATA_MESSAGE = "errorDataMessage";
    public static final String PARAM_CONFIRM_ERROR = "confirmError";
    public static final String ERROR_APPROVE = "approveError";
    public static final String NOT_FOUND_ORDERS = "notFoundOrders";
    public static final String AVATAR_ERROR = "errorAvatar";

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
    public static final String CHANGE_SAVED = "ChangedSave";
    public static final String LOGIN_ERROR = "loginError";
    public static final String NAME_ERROR = "usernameError";
    public static final String SURNAME_ERROR = "surnameError";

    //book
    public static final String ALL_BOOKS = "allBooks";
    public static final String BOOK = "book";

    public static final String OVERVIEW_ERROR = "overviewError";
    public static final String ALL_CATEGORIES = "allCategories";
    public static final String BOOK_PARAMETER = "bookParameter";
    public static final String CORRECT_BOOK_MESSAGE = "createBookSuccess";

    //author
    public static final String ALL_AUTHORS = "allAuthors";
    public static final String CREATE_TYPE = "createType";
    public static final String CORRECT_DATA_MESSAGE = "createAuthorSuccess";

    //order
    public static final String USER_ID = "userId";
    public static final String ORDER_TYPE = "orderType";
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
