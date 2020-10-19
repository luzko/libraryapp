package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.Book;

/**
 * The interface Book dao.
 */
public interface BookDao extends BaseDao<Book> {
    /**
     * Check parameter is unique.
     *
     * @param title the title
     * @param year  the year
     * @param pages the pages
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isParameterUnique(String title, int year, int pages) throws DaoException;

    /**
     * Add.
     *
     * @param book     the book
     * @param authorId the author id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Book book, long authorId) throws DaoException;
}
