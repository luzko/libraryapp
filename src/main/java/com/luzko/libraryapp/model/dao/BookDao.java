package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * The interface Book dao.
 */
public interface BookDao extends BaseDao {
    /**
     * Find count records.
     *
     * @return the int
     * @throws DaoException the dao exception
     */
    int findCount() throws DaoException;

    /**
     * Find part.
     *
     * @param recordsShown   the records shown
     * @param recordsPerPage the records per page
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Book> findPartOfAll(int recordsShown, int recordsPerPage) throws DaoException;

    Optional<Book> findById(long bookId) throws DaoException;

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
