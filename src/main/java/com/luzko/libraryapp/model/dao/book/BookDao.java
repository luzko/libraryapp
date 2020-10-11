package com.luzko.libraryapp.model.dao.book;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.dao.BaseDao;
import com.luzko.libraryapp.model.entity.book.Book;

public interface BookDao extends BaseDao<Book> {
    boolean isParameterUnique(String title, int year, int pages) throws DaoException;

    boolean add(Book book) throws DaoException;
}
