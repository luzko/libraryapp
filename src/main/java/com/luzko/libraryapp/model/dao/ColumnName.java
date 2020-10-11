package com.luzko.libraryapp.model.dao;

public class ColumnName {
    private ColumnName() {

    }

    //user
    public static final String USER_ID = "user_id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE_ID_FK = "role_id_fk";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String EMAIL = "email";
    public static final String USER_STATUS_ID_FK = "user_status_id_fk";
    public static final String CONFIRM_CODE = "confirm";
    public static final String COUNT = "count";

    //book
    public static final String BOOK_ID = "book_id";
    public static final String TITLE = "title";
    public static final String YEAR = "year";
    public static final String PAGES = "pages";
    public static final String DESCRIPTION = "description";
    public static final String NUMBER_COPIES = "number";
    public static final String CATEGORY_ID_FK = "category_id_fk";
    public static final String CATEGORY = "category";
    public static final String AUTHORS = "authors";
    public static final String BOOK_COUNT = "count";

    //author
    public static final String AUTHOR_ID = "author_id";
    public static final String AUTHOR = "author";
    public static final String AUTHOR_COUNT = "count";
}
