package com.luzko.libraryapp.model.dao;

public class StatementSql {
    private StatementSql() {
    }

    public static final String ADD_USER =
            "INSERT INTO users(login, password, role_id_fk, name, surname, email, user_status_id_fk, confirm) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String FIND_PASSWORD_BY_LOGIN =
            "SELECT password FROM users WHERE login LIKE ?";

    public static final String FIND_CODE_BY_LOGIN =
            "SELECT confirm FROM users WHERE login LIKE ?";

    public static final String FIND_COUNT_BY_LOGIN =
            "SELECT count(user_id) as count FROM users WHERE login LIKE ?";

    public static final String FIND_USER_BY_LOGIN =
            "SELECT user_id, login, role_id_fk, name, surname, email, user_status_id_fk " +
                    "FROM users WHERE login LIKE ?";

    public static final String FIND_ALL_USERS =
            "SELECT user_id, login, role_id_fk, name, surname, email, user_status_id_fk" +
                    " FROM users WHERE role_id_fk != 1 ORDER BY role_id_fk";

    public static final String CHANGE_USER_STATUS =
            "UPDATE users SET user_status_id_fk = ? WHERE login LIKE ?";
}
