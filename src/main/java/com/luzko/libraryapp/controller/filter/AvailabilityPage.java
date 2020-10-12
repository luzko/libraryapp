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
    private static final String ADMIN = "admin.jsp";
    private static final String CONFIRMATION = "confirmation.jsp";
    private static final String BLOCKED = "blocked.jsp";
    private static final String CREATE_BOOK = "createbook.jsp";

    public static List<String> availableGustPage = List.of(
            INDEX, HOME, LOGIN, REGISTRATION
    );
    public static List<String> availableReaderPage = List.of(
            INDEX, HOME, USER, CONFIRMATION, BLOCKED
    );
    public static List<String> availableLibrarianPage = List.of(
            INDEX, HOME, USER, CONFIRMATION, BLOCKED
    );
    public static List<String> availableAdminPage = List.of(
            INDEX, HOME, REGISTRATION, ADMIN, CREATE_BOOK
    );
    public static List<String> availableBlockedPage = List.of(
            INDEX, HOME, BLOCKED
    );
    public static List<String> availableUnconfirmedPage = List.of(
            INDEX, HOME, CONFIRMATION
    );
}