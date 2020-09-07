package com.luzko.libraryapp.model.dao;

public class StatementSql {
    private StatementSql(){
    }

    public static final String INSERT_USER =
            "INSERT INTO users(name, email, password) VALUES(?,?,?)";

    public static final String FIND_USER_BY_EMAIL =
            "SELECT name, email, password, registered FROM users WHERE enabled = true AND email = ?";

}
