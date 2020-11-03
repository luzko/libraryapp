package com.luzko.libraryapp.controller;

public class PagePath {
    private PagePath() {

    }

    //view
    public static final String INDEX = "/index.jsp";
    public static final String HOME = "/jsp/view/home.jsp";
    public static final String LOGIN = "/jsp/view/login.jsp";
    public static final String REGISTRATION = "/jsp/view/registration.jsp";
    public static final String USER = "/jsp/view/user.jsp";
    public static final String ADMIN = "/jsp/view/admin.jsp";
    public static final String LIBRARY = "/jsp/view/library.jsp";
    public static final String CONFIRMATION = "/jsp/view/confirmation.jsp";
    public static final String BLOCKED = "/jsp/view/blocked.jsp";
    public static final String BOOK_OVERVIEW = "/jsp/view/bookoverview.jsp";
    public static final String CREATE_BOOK = "/jsp/view/createbook.jsp";
    public static final String ORDERS = "/jsp/view/orders.jsp";

    //error
    public static final String ERROR = "/jsp/error/error500.jsp";
    public static final String ERROR_STEP = "/jsp/error/error404.jsp";
}

