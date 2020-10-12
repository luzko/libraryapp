package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.Book;

public interface BookDao extends BaseDao<Book> {
    boolean isParameterUnique(String title, int year, int pages) throws DaoException;

    boolean add(Book book, long authorId) throws DaoException;
}
