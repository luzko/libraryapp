package com.luzko.libraryapp.controller.filter;

import java.util.List;

public class AvailabilityPage {

    AvailabilityPage() {

    }

    private static final String INDEX = "index.jsp";
    private static final String HOME = "home.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String REGISTRATION = "registration.jsp";
    private static final String USER = "user.jsp";
    private static final String CONFIRMATION = "confirmation.jsp";
    private static final String BLOCKED = "blocked.jsp";
    private static final String CREATE_BOOK = "createbook.jsp";

    public static final String BOOK_OVERVIEW = "bookoverview.jsp";
    public static final String ORDERS = "orders.jsp";
    public static final String ADMIN = "admin.jsp";

    public static final List<String> availableGustPageList = List.of(
            INDEX, HOME, LOGIN, REGISTRATION
    );
    public static final List<String> availableReaderPageList = List.of(
            INDEX, HOME, USER, CONFIRMATION, BLOCKED, BOOK_OVERVIEW, ORDERS
    );
    public static final List<String> availableLibrarianPageList = List.of(
            INDEX, HOME, USER, CONFIRMATION, BLOCKED, BOOK_OVERVIEW, ORDERS
    );
    public static final List<String> availableAdminPageList = List.of(
            INDEX, HOME, REGISTRATION, ADMIN, CREATE_BOOK
    );
    public static final List<String> availableBlockedPageList = List.of(
            INDEX, HOME, BLOCKED
    );
    public static final List<String> availableUnconfirmedPageList = List.of(
            INDEX, HOME, CONFIRMATION
    );
}