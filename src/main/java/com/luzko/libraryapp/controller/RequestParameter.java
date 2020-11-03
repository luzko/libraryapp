package com.luzko.libraryapp.controller;

public class RequestParameter {
    private RequestParameter() {

    }

    //general
    public static final String COMMAND_NAME = "command";
    public static final String CODE = "code";

    //user
    public static final String LOGIN = "login";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String USER_STATUS = "status";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";

    //book
    public static final String BOOK_ID = "book_id";
    public static final String TITLE = "title";
    public static final String YEAR = "year";
    public static final String PAGES = "pages";
    public static final String DESCRIPTION = "description";
    public static final String NUMBER_COPIES = "number";
    public static final String CATEGORY = "category";
    public static final String AUTHOR = "author";
    public static final String SEARCH = "search";

    //message
    public static final String SUBJECT = "subject";
    public static final String MESSAGE = "message";
    public static final String TYPE_MESSAGE = "typeMessage";

    //author
    public static final String CREATE_TYPE = "createType";
    public static final String AUTHOR_NAME = "authorName";

    //order
    public static final String ORDER_TYPE = "orderType";
    public static final String ORDER_ID = "orderId";
    public static final String USER_ID = "userId";
    public static final String CREATE_ORDER_TYPE = "typeCreateOrder";

    //pagination
    public static final String CURRENT_PAGE = "currentPage";
}