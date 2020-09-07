DROP DATABASE IF EXISTS library;
CREATE DATABASE library;

use library;

DROP TABLE IF EXISTS book_category;
CREATE TABLE book_category
(
  id   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL,
  name VARCHAR(40) 				   	   NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS book;
CREATE TABLE book
(
	id   		INT UNSIGNED AUTO_INCREMENT NOT NULL,
    title 		VARCHAR(40) 				NOT NULL,
    year 		SMALLINT UNSIGNED 			NOT NULL,
    pages 		SMALLINT UNSIGNED 			NOT NULL,
    description VARCHAR(500) 				NOT NULL,
    category_id TINYINT UNSIGNED 			NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (title, year, pages, description, category_id),
    FOREIGN KEY (category_id) REFERENCES book_category (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS author;
CREATE TABLE author
(
  id   		 INT UNSIGNED AUTO_INCREMENT NOT NULL,
  first_name VARCHAR(20) 		   		 NOT NULL,
  last_name  VARCHAR(20) 		   		 NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS book_author;
CREATE TABLE book_author
(
	id   	  INT UNSIGNED AUTO_INCREMENT NOT NULL,
    book_id   INT UNSIGNED 				  NOT NULL,
    author_id INT UNSIGNED				  NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS book_copy;
CREATE TABLE book_copy
(
	id   	  INT UNSIGNED AUTO_INCREMENT NOT NULL,
    book_id   INT UNSIGNED 				  NOT NULL,
    available BOOL 			 			  NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),
	FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role
(
  id   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL,
  name VARCHAR(20) 				   	   NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
  id         INT UNSIGNED AUTO_INCREMENT NOT NULL,
  login      VARCHAR(20) 				 NOT NULL,
  password   VARCHAR(20) 				 NOT NULL,
  role_id    TINYINT UNSIGNED 			 NOT NULL,
  first_name VARCHAR(20) 				 NOT NULL,
  last_name  VARCHAR(20) 				 NOT NULL,
  address 	 VARCHAR(60) 				 NOT NULL,
  email 	 VARCHAR(40) 				 NOT NULL,
  phone 	 VARCHAR(20) 				 NOT NULL,
  enabled 	 BOOL 			 			 NOT NULL DEFAULT TRUE,
  registered DATETIME  		 			 NOT NULL DEFAULT NOW(),
  PRIMARY KEY (id),
  UNIQUE KEY (login),
  UNIQUE KEY (email),
  FOREIGN KEY (role_id) REFERENCES user_role (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS book_order;
CREATE TABLE book_order
(
	id           INT UNSIGNED AUTO_INCREMENT NOT NULL,
    user_id 	 INT UNSIGNED 				 NOT NULL,
    book_copy_id INT UNSIGNED 			     NOT NULL,
    status       BOOL 			 			 NOT NULL DEFAULT FALSE,
    reading_room BOOL 			 			 NOT NULL DEFAULT FALSE,
    date 	 	 DATETIME  		 			 NOT NULL DEFAULT NOW(),
    take_date 	 DATETIME  		 			 NOT NULL,
    return_date  DATETIME  		 			 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (book_copy_id) REFERENCES book_copy (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

