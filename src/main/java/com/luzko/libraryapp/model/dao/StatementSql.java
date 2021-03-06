package com.luzko.libraryapp.model.dao;

public class StatementSql {
    private StatementSql() {

    }

    //users query
    public static final String ADD_USER =
            "INSERT INTO users(login, password, role_id_fk, name, surname, email, user_status_id_fk, confirm) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String CHANGE_PASSWORD =
            "UPDATE users SET password = ? WHERE login LIKE ?";

    public static final String FIND_PASSWORD_BY_LOGIN =
            "SELECT password FROM users WHERE login LIKE ?";

    public static final String FIND_CODE_BY_LOGIN =
            "SELECT confirm FROM users WHERE login LIKE ?";

    public static final String FIND_COUNT_BY_LOGIN =
            "SELECT count(user_id) count FROM users WHERE login LIKE ?";

    public static final String FIND_USER_BY_LOGIN =
            "SELECT user_id, login, role_id_fk, name, surname, email, user_status_id_fk, avatar " +
                    "FROM users WHERE login LIKE ?";

    public static final String FIND_PART_USERS =
            "SELECT user_id, login, role_id_fk, name, surname, email, user_status_id_fk, avatar " +
                    "FROM users WHERE role_id_fk != 1 ORDER BY role_id_fk " +
                    "LIMIT ? OFFSET ?";

    public static final String FIND_USERS =
            "SELECT user_id, login, role_id_fk, name, surname, email, user_status_id_fk, avatar " +
                    "FROM users WHERE role_id_fk != 1 AND login LIKE ? ORDER BY role_id_fk " +
                    "LIMIT ? OFFSET ?";

    public static final String FIND_COUNT_USER =
            "SELECT count(*) count FROM users WHERE role_id_fk != 1";

    public static final String FIND_COUNT_SEARCH_USER =
            "SELECT count(*) count FROM users WHERE role_id_fk != 1 AND login LIKE ?";

    public static final String CHANGE_USER_STATUS =
            "UPDATE users SET user_status_id_fk = ? WHERE login LIKE ?";

    public static final String CHANGE_USER_LOGIN =
            "UPDATE users SET login = ? WHERE login LIKE ?";

    public static final String CHANGE_USER_NAME =
            "UPDATE users SET name = ? WHERE login LIKE ?";

    public static final String CHANGE_USER_SURNAME =
            "UPDATE users SET surname = ? WHERE login LIKE ?";

    public static final String CHANGE_USER_AVATAR =
            "UPDATE users SET avatar = ? WHERE login like ?";

    //books query
    public static final String FIND_PART_BOOKS =
            "SELECT b.book_id, b.title, b.category_id_fk, " +
                    "GROUP_CONCAT(DISTINCT a.author ORDER BY a.author SEPARATOR ', ') authors FROM books b " +
                    "LEFT JOIN book_authors ba on b.book_id = ba.book_id_fk " +
                    "LEFT JOIN authors a on a.author_id = ba.author_id_fk " +
                    "WHERE b.enabled = TRUE GROUP BY b.book_id ORDER BY title " +
                    "LIMIT ? OFFSET ?";

    public static final String FIND_BY_NAME =
            "SELECT b.book_id, b.title, b.category_id_fk, " +
                    "GROUP_CONCAT(DISTINCT a.author ORDER BY a.author SEPARATOR ', ') authors FROM books b " +
                    "LEFT JOIN book_authors ba on b.book_id = ba.book_id_fk " +
                    "LEFT JOIN authors a on a.author_id = ba.author_id_fk " +
                    "WHERE b.enabled = TRUE AND title LIKE ? GROUP BY b.book_id ORDER BY title " +
                    "LIMIT ? OFFSET ?";

    public static final String FIND_BOOK_BY_ID =
            "SELECT b.book_id, b.title, b.year, b.pages, b.description, b.number_copies, b.category_id_fk, " +
                    "GROUP_CONCAT(DISTINCT a.author ORDER BY a.author SEPARATOR ', ') authors FROM books b " +
                    "LEFT JOIN book_authors ba ON b.book_id = ba.book_id_fk " +
                    "LEFT JOIN authors a ON a.author_id = ba.author_id_fk " +
                    "WHERE b.enabled = TRUE AND b.book_id LIKE ? GROUP BY b.book_id";

    public static final String FIND_COUNT_BY_PARAMETER =
            "SELECT count(book_id) count FROM books WHERE title LIKE ? AND year LIKE ? AND pages LIKE ? AND enabled = TRUE";

    public static final String FIND_BOOK_BY_PARAMETER =
            "SELECT book_id FROM books WHERE title LIKE ? AND year LIKE ? AND page LIKE ? AND enabled = TRUE";

    public static final String COUNT_BOOK_BY_ID =
            "SELECT number_copies FROM books WHERE book_id = ? AND enabled = TRUE AND enabled = TRUE";

    public static final String FIND_COUNT_BOOK =
            "SELECT count(*) count FROM books WHERE enabled = TRUE";

    public static final String FIND_COUNT_SEARCH_BOOK =
            "SELECT count(*) count FROM books WHERE enabled = TRUE AND title LIKE ?";

    public static final String ADD_BOOK =
            "INSERT INTO books(title, year, pages, description, number_copies, category_id_fk) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String RETURN_BOOK =
            "UPDATE books SET number_copies = number_copies + 1 WHERE book_id = ?";

    public static final String APPROVE_BOOK =
            "UPDATE books SET number_copies = number_copies - 1 WHERE book_id = ?";

    public static final String REMOVE_BOOK =
            "UPDATE books SET enabled = FALSE WHERE book_id = ?";

    //authors query
    public static final String FIND_ALL_AUTHORS =
            "SELECT author_id, author FROM authors";

    public static final String ADD_AUTHOR =
            "INSERT INTO authors(author) VALUES (?)";

    public static final String FIND_COUNT_BY_NAME =
            "SELECT count(author_id) count FROM authors WHERE author LIKE ?";

    //book authors query
    public static final String ADD_BOOK_AUTHORS =
            "INSERT INTO book_authors(book_id_fk, author_id_fk) " +
                    "VALUES (?, ?)";

    //orders query
    public static final String FIND_ALL_ORDERS =
            "SELECT o.order_id, u.user_id, u.login, b.book_id, b.title, o.order_date, o.return_date, o.order_status_id_fk status, o.order_types_id_fk type " +
                    "FROM orders o LEFT JOIN users u ON o.user_id_fk = u.user_id " +
                    "LEFT JOIN books b ON b.book_id = o.book_id_fk " +
                    "WHERE o.enabled = TRUE ORDER BY order_date DESC LIMIT ? OFFSET ?";

    public static final String FIND_COUNT_ALL_ORDERS =
            "SELECT count(order_id) count FROM orders WHERE enabled = TRUE ORDER BY order_date DESC";

    public static final String FIND_NEW_ORDERS =
            "SELECT o.order_id, u.user_id, u.login, b.book_id, b.title, o.order_date, o.return_date, o.order_status_id_fk status, o.order_types_id_fk type " +
                    "FROM orders o LEFT JOIN users u ON o.user_id_fk = u.user_id " +
                    "LEFT JOIN books b ON b.book_id = o.book_id_fk " +
                    "WHERE o.enabled = TRUE AND order_status_id_fk = 1 ORDER BY order_date " +
                    "LIMIT ? OFFSET ?";

    public static final String FIND_COUNT_NEW_ORDERS =
            "SELECT count(order_id) count FROM orders WHERE enabled = TRUE AND order_status_id_fk = 1 ORDER BY order_date";

    public static final String FIND_ORDERS_BY_USER =
            "SELECT o.order_id, b.book_id, b.title, o.order_date, o.order_status_id_fk status, o.order_types_id_fk type FROM orders o " +
                    "LEFT JOIN books b on o.book_id_fk = b.book_id " +
                    "WHERE o.enabled = TRUE AND o.user_id_fk LIKE ? ORDER BY order_date DESC " +
                    "LIMIT ? OFFSET ?";

    public static final String FIND_COUNT_ORDERS_BY_USER =
            "SELECT count(order_id) count FROM orders WHERE user_id_fk LIKE ? ORDER BY order_date DESC";

    public static final String FIND_ORDER_BY_BOOK =
            "SELECT o.order_id, u.login, o.order_date, o.return_date, o.order_status_id_fk status, o.order_types_id_fk type " +
                    "FROM orders o LEFT JOIN users u ON o.user_id_fk = u.user_id " +
                    "WHERE o.enabled = TRUE AND o.book_id_fk LIKE ? ORDER BY order_date DESC " +
                    "LIMIT ? OFFSET ?";

    public static final String FIND_COUNT_ORDERS_BY_BOOK =
            "SELECT count(order_id) count FROM orders WHERE book_id_fk LIKE ? ORDER BY order_date DESC";

    public static final String CHANGE_STATUS_ORDER =
            "UPDATE orders SET order_status_id_fk = ? WHERE order_id LIKE ?";

    public static final String RETURN_ORDER =
            "UPDATE orders SET order_status_id_fk = 5, return_date = ? WHERE order_id LIKE ?";

    public static final String CANCEL_ORDER_BY_BOOK =
            "UPDATE orders SET order_status_id_fk = 4 WHERE order_status_id_fk = 1 AND book_id_fk LIKE ?";

    public static final String COUNT_ORDERS_BY_ID =
            "SELECT count(order_id) count FROM orders WHERE user_id_fk LIKE ? AND order_status_id_fk = 2";

    public static final String CREATE_ORDER =
            "INSERT INTO orders (user_id_fk, book_id_fk, order_types_id_fk, order_date) VALUES (?, ?, ?, ?)";

    public static final String GIVE_ALL_BOOK =
            "UPDATE orders SET order_status_id_fk = ?, return_date = ? WHERE user_id_fk LIKE ? " +
                    "AND order_status_id_fk LIKE ? AND order_types_id_fk LIKE ?";
}

