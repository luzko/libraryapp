package com.luzko.libraryapp.model.dao;

public class StatementSql {
    private StatementSql() {
    }


    public static final String FIND_USER_BY_LOGIN =
            "SELECT user_id, login, password, role_id_fk, name, surname, email " +
                    "FROM users " +
                    "WHERE enabled = TRUE AND login LIKE ?";

    public static final String FIND_USER_BY_EMAIL =
            "SELECT user_id, login, password, role_id_fk, name, surname, email " +
                    "FROM users " +
                    "WHERE enabled = TRUE AND email LIKE ?";

    public static final String ADD_USER =
            "INSERT INTO users(login, password, role_id_fk, name, surname, email, enabled) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";


}
