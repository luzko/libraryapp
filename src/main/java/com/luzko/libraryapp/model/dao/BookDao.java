package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * The interface represents Book DAO.
 */
public interface BookDao extends BaseDao {
    /**
     * Find count records.
     *
     * @return the count records
     * @throws DaoException the dao exception
     */
    int findCount() throws DaoException;

    /**
     * Find count records.
     *
     * @param searchName the search value
     * @return the count records
     * @throws DaoException the dao exception
     */
    int findCount(String searchName) throws DaoException;

    /**
     * Find part books.
     *
     * @param recordsShown   the records shown
     * @param recordsPerPage the records per page
     * @return the list of found books
     * @throws DaoException the dao exception
     */
    List<Book> findPartOfAll(int recordsShown, int recordsPerPage) throws DaoException;

    /**
     * Find by name books.
     *
     * @param searchName     the search value
     * @param recordsShown   the records shown
     * @param recordsPerPage the records per page
     * @return the list of found books
     * @throws DaoException the dao exception
     */
    List<Book> findByName(String searchName, int recordsShown, int recordsPerPage) throws DaoException;

    /**
     * Find book by id.
     *
     * @param bookId the book id
     * @return the optional of found Book
     * @throws DaoException the dao exception
     */
    Optional<Book> findById(long bookId) throws DaoException;

    /**
     * Check parameter is unique.
     *
     * @param title the book title
     * @param year  the book year
     * @param pages the book pages
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isParameterUnique(String title, int year, int pages) throws DaoException;

    /**
     * Add new book.
     *
     * @param book     the book
     * @param authorId the author id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(Book book, long authorId) throws DaoException;

    /**
     * Remove book.
     *
     * @param bookId the book id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean remove(long bookId) throws DaoException;
}
