DROP DATABASE IF EXISTS library;
CREATE DATABASE library CHARACTER SET utf8;

use library;

DROP TABLE IF EXISTS categories;
CREATE TABLE categories
(
    category_id  TINYINT UNSIGNED AUTO_INCREMENT NOT NULL,
    category     VARCHAR(40)                     NOT NULL,
    PRIMARY KEY  (category_id)
);

DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    book_id         INT UNSIGNED AUTO_INCREMENT NOT NULL,
    title           VARCHAR(40)                 NOT NULL,
    year            SMALLINT UNSIGNED           NOT NULL,
    pages           SMALLINT UNSIGNED           NOT NULL,
    description     VARCHAR(500)                NOT NULL,
    number_copies   TINYINT UNSIGNED            NOT NULL,
    category_id_fk  TINYINT UNSIGNED            NOT NULL,
    enabled         BOOL                        NOT NULL DEFAULT TRUE,
    PRIMARY KEY (book_id),
    UNIQUE KEY  (title, year, pages),
    FOREIGN KEY (category_id_fk) REFERENCES categories (category_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    author_id    INT UNSIGNED AUTO_INCREMENT NOT NULL,
    author       VARCHAR(20)                 NOT NULL,
    UNIQUE  KEY (author),
    PRIMARY KEY (author_id)
);

DROP TABLE IF EXISTS book_authors;
CREATE TABLE book_authors
(
    book_author_id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    book_id_fk     INT UNSIGNED                NOT NULL,
    author_id_fk   INT UNSIGNED                NOT NULL,
    PRIMARY KEY (book_author_id),
    UNIQUE KEY  (book_id_fk, author_id_fk),
    FOREIGN KEY (book_id_fk)   REFERENCES books (book_id)     ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (author_id_fk) REFERENCES authors (author_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
    role_id TINYINT UNSIGNED AUTO_INCREMENT NOT NULL,
    role    VARCHAR(20)                     NOT NULL,
    UNIQUE  KEY (role),
    PRIMARY KEY (role_id)
);

DROP TABLE IF EXISTS user_statuses;
CREATE TABLE user_statuses
(
    user_status_id TINYINT UNSIGNED AUTO_INCREMENT NOT NULL,
    user_status    VARCHAR(20)                     NOT NULL,
    UNIQUE  KEY (user_status),
    PRIMARY KEY (user_status_id)
);

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    user_id           INT UNSIGNED AUTO_INCREMENT NOT NULL,
    login             VARCHAR(20)                 NOT NULL,
    password          VARCHAR(60)                 NOT NULL,
    role_id_fk        TINYINT UNSIGNED            NOT NULL,
    name              VARCHAR(20)                 NOT NULL,
    surname           VARCHAR(20)                 NOT NULL,
    email             VARCHAR(40)                 NOT NULL,
    user_status_id_fk TINYINT UNSIGNED            NOT NULL,
    confirm           VARCHAR(40)                 NOT NULL,
    UNIQUE  KEY (login),
    PRIMARY KEY (user_id),
    FOREIGN KEY (role_id_fk)        REFERENCES roles (role_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (user_status_id_fk) REFERENCES user_statuses (user_status_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS order_statuses;
CREATE TABLE order_statuses
(
    order_status_id TINYINT UNSIGNED AUTO_INCREMENT NOT NULL,
    status          VARCHAR(20) NOT NULL,
    UNIQUE  KEY (status),
    PRIMARY KEY (order_status_id)
);

DROP TABLE IF EXISTS order_types;
CREATE TABLE order_types
(
    order_types_id TINYINT UNSIGNED AUTO_INCREMENT NOT NULL,
    type           VARCHAR(20) NOT NULL,
    UNIQUE  KEY (type),
    PRIMARY KEY (order_types_id)
);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    order_id           INT UNSIGNED AUTO_INCREMENT NOT NULL,
    user_id_fk         INT UNSIGNED                NOT NULL,
    book_id_fk         INT UNSIGNED                NOT NULL,
    order_status_id_fk TINYINT UNSIGNED            NOT NULL DEFAULT 1,
    order_types_id_fk  TINYINT UNSIGNED            NOT NULL,
    order_date         BIGINT                      NOT NULL,
    return_date        BIGINT                      NULL DEFAULT NULL,
    enabled            BOOL                        NOT NULL DEFAULT TRUE,
    PRIMARY KEY (order_id),
    FOREIGN KEY (user_id_fk)         REFERENCES users (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (book_id_fk)         REFERENCES books (book_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (order_status_id_fk) REFERENCES order_statuses (order_status_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (order_types_id_fk)  REFERENCES order_types (order_types_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);