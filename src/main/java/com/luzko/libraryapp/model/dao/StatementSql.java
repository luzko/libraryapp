package com.luzko.libraryapp.model.dao;

public class StatementSql {
    private StatementSql() {
    }

    public static final String INSERT_USER =
            "";

    public static final String FIND_USER_BY_EMAIL =
            "";

    public static final String FIND_USER_BY_LOGIN =
            "SELECT user_id, login, password FROM users WHERE login LIKE ?";

    public static final String FIND_PASS_BY_LOGIN =
            "SELECT password FROM users WHERE login LIKE ?";


}
