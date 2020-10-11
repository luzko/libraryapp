package com.luzko.libraryapp.model.dao;

public class StatementSql {
    private StatementSql() {

    }

    //user query
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

    public static final String FIND_USER_BY_ID =
            "SELECT user_id, login, role_id_fk, name, surname, email, user_status_id_fk " +
                    "FROM users WHERE user_id LIKE ?";

    public static final String FIND_ALL_USERS =
            "SELECT user_id, login, role_id_fk, name, surname, email, user_status_id_fk " +
                    "FROM users WHERE role_id_fk != 1 ORDER BY role_id_fk";

    public static final String CHANGE_USER_STATUS =
            "UPDATE users SET user_status_id_fk = ? WHERE login LIKE ?";


    public static final String CHANGE_USER_LOGIN =
            "UPDATE users SET login = ? WHERE login LIKE ?";

    public static final String CHANGE_USER_NAME =
            "UPDATE users SET name = ? WHERE login LIKE ?";

    public static final String CHANGE_USER_SURNAME =
            "UPDATE users SET surname = ? WHERE login LIKE ?";

    //book query
    public static final String FIND_ALL_BOOKS =
            "SELECT b.book_id, b.title, b.category_id_fk, " +
                    "GROUP_CONCAT(DISTINCT a.author ORDER BY a.author SEPARATOR ', ') authors FROM books b " +
                    "LEFT JOIN book_authors ba on b.book_id = ba.book_id_fk " +
                    "LEFT JOIN authors a on a.author_id = ba.author_id_fk " +
                    "WHERE b.enabled = TRUE GROUP BY b.book_id";

    public static final String FIND_BOOK_BY_ID =
            "SELECT b.book_id, b.title, b.year, b.pages, b.description, b.number_copies, b.category_id_fk, " +
                    "GROUP_CONCAT(DISTINCT a.author ORDER BY a.author SEPARATOR ', ') authors FROM books b " +
                    "LEFT JOIN book_authors ba on b.book_id = ba.book_id_fk " +
                    "LEFT JOIN authors a on a.author_id = ba.author_id_fk " +
                    "WHERE b.enabled = TRUE AND b.book_id LIKE ? GROUP BY b.book_id";

    public static final String FIND_COUNT_BY_PARAMETER =
            "SELECT count(book_id) as count FROM books WHERE title LIKE ? AND year LIKE ? AND pages LIKE ?";

    //author query
    public static final String FIND_ALL_AUTHORS =
            "SELECT author_id, author FROM authors";

    public static final String FIND_AUTHOR_BY_ID =
            "SELECT author_id, author FROM authors WHERE author_id LIKE ?";

    public static final String ADD_AUTHOR =
            "INSERT INTO authors(author) VALUES (?)";

    public static final String FINE_COUNT_BY_NAME =
            "SELECT count(author_id) as count FROM authors WHERE author LIKE ?";

}


