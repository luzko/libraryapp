package com.luzko.libraryapp.filter;

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
    private static final String LIBRARY = "library.jsp";
    private static final String CONFIRMATION = "confirmation.jsp";
    private static final String BLOCKED = "blocked.jsp";
    private static final String BOOK_OVERVIEW = "bookoverview.jsp";
    private static final String CREATE_BOOK = "createbook.jsp";

    public static List<String> availableGustPage = List.of(
            INDEX, HOME, LOGIN, REGISTRATION, LIBRARY, BOOK_OVERVIEW
    );
    public static List<String> availableReaderPage = List.of(
            INDEX, HOME, LOGIN, REGISTRATION, LIBRARY, BOOK_OVERVIEW, USER, CONFIRMATION, BLOCKED
    );
    public static List<String> availableLibrarianPage = List.of(
            INDEX, HOME, LOGIN, REGISTRATION, LIBRARY, BOOK_OVERVIEW, USER, CONFIRMATION, BLOCKED
    );
    public static List<String> availableAdminPage = List.of(
            INDEX, HOME, LOGIN, REGISTRATION, LIBRARY, BOOK_OVERVIEW, ADMIN, CREATE_BOOK
    );
}
