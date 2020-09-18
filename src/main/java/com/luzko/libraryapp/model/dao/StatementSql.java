package com.luzko.libraryapp.model.dao;

public class StatementSql {
    private StatementSql() {
    }

    public static final String FIND_PASSWORD_BY_LOGIN =
            "SELECT password FROM users WHERE login LIKE ?";

    public static final String FIND_USER_BY_LOGIN =
            "SELECT user_id, login, role_id_fk, name, surname, email, enabled FROM users WHERE login LIKE ?";

    public static final String ADD_USER =
            "INSERT INTO users(login, password, role_id_fk, name, surname, email, enabled) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
}
