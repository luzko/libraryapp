package com.luzko.libraryapp.model.dao;

public class StatementSql {
    private StatementSql() {

    }

    //users query
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

    //books query
    public static final String FIND_ALL_BOOKS =
            "SELECT b.book_id, b.title, b.category_id_fk, " +
                    "GROUP_CONCAT(DISTINCT a.author ORDER BY a.author SEPARATOR ', ') authors FROM books b " +
                    "LEFT JOIN book_authors ba on b.book_id = ba.book_id_fk " +
                    "LEFT JOIN authors a on a.author_id = ba.author_id_fk " +
                    "WHERE b.enabled = TRUE GROUP BY b.book_id";

    public static final String FIND_BOOK_BY_ID =
            "SELECT b.book_id, b.title, b.year, b.pages, b.description, b.number_copies, b.category_id_fk, " +
                    "GROUP_CONCAT(DISTINCT a.author ORDER BY a.author SEPARATOR ', ') authors FROM books b " +
                    "LEFT JOIN book_authors ba ON b.book_id = ba.book_id_fk " +
                    "LEFT JOIN authors a ON a.author_id = ba.author_id_fk " +
                    "WHERE b.enabled = TRUE AND b.book_id LIKE ? GROUP BY b.book_id";

    public static final String FIND_COUNT_BY_PARAMETER =
            "SELECT count(book_id) as count FROM books WHERE title LIKE ? AND year LIKE ? AND pages LIKE ?";

    public static final String FIND_BOOK_BY_PARAMETER =
            "SELECT book_id FROM books WHERE title LIKE ? AND year LIKE ? AND page LIKE ?";

    public static final String COUNT_BOOK_BY_ID = "SELECT number_copies FROM books WHERE book_id = ?";

    public static final String ADD_BOOK =
            "INSERT INTO books(title, year, pages, description, number_copies, category_id_fk) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String RETURN_BOOK = "UPDATE books SET number_copies = number_copies + 1 WHERE book_id = ?";

    //authors query
    public static final String FIND_ALL_AUTHORS =
            "SELECT author_id, author FROM authors";

    public static final String FIND_AUTHOR_BY_ID =
            "SELECT author_id, author FROM authors WHERE author_id LIKE ?";

    public static final String ADD_AUTHOR =
            "INSERT INTO authors(author) VALUES (?)";

    public static final String FINE_COUNT_BY_NAME =
            "SELECT count(author_id) as count FROM authors WHERE author LIKE ?";

    //book authors query
    public static final String ADD_BOOK_AUTHORS =
            "INSERT INTO book_authors(book_id_fk, author_id_fk) " +
                    "VALUES (?, ?)";

    //orders query
    public static final String FIND_ALL_ORDERS =
            "SELECT o.order_id, u.login, b.title, o.order_date, o.return_date, o.order_status_id_fk status, o.order_types_id_fk type " +
                    "FROM orders o LEFT JOIN users u ON o.user_id_fk = u.user_id " +
                    "LEFT JOIN books b ON b.book_id = o.book_id_fk " +
                    "WHERE o.enabled = TRUE";

    public static final String FIND_NEW_ORDERS =
            "SELECT o.order_id, u.login, b.title, o.order_date, o.return_date, o.order_status_id_fk status, o.order_types_id_fk type " +
                    "FROM orders o LEFT JOIN users u ON o.user_id_fk = u.user_id " +
                    "LEFT JOIN books b ON b.book_id = o.book_id_fk " +
                    "WHERE o.enabled = TRUE AND order_status_id_fk = 1";

    public static final String FIND_ORDERS_BY_USER_ID =
            "SELECT o.order_id, b.book_id, b.title, o.order_date, o.order_status_id_fk status, o.order_types_id_fk type FROM orders o " +
                    "LEFT JOIN books b on o.book_id_fk = b.book_id " +
                    "WHERE o.enabled = TRUE AND o.user_id_fk LIKE ?";

    public static final String FIND_ORDER_BY_BOOK_ID =
            "SELECT o.order_id, u.login, o.order_date, o.return_date, o.order_status_id_fk status, o.order_types_id_fk type " +
                    "FROM orders o LEFT JOIN users u ON o.user_id_fk = u.user_id " +
                    "WHERE o.enabled = TRUE AND o.book_id_fk LIKE ?";

    public static final String CHANGE_STATUS_ORDER =
            "UPDATE orders SET order_status_id_fk = ? WHERE order_id LIKE ?";

    public static final String RETURN_ORDER =
            "UPDATE orders SET order_status_id_fk = 5, return_date = ? WHERE order_id LIKE ?";

    public static final String COUNT_ORDERS_BY_ID =
            "SELECT count(order_id) as count FROM orders WHERE user_id_fk LIKE ? AND order_status_id_fk = 2";

    public static final String CREATE_ORDER =
            "INSERT INTO orders (user_id_fk, book_id_fk, order_types_id_fk, order_date) VALUES (?, ?, ?, ?)";
}

