package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface represents Book Service.
 */
public interface BookService {
    /**
     * Find count records.
     *
     * @return the count records
     * @throws ServiceException the service exception
     */
    int findCountRecords() throws ServiceException;

    /**
     * Find count records.
     *
     * @param searchName the search value
     * @return the count records
     * @throws ServiceException the service exception
     */
    int findCountRecords(String searchName) throws ServiceException;

    /**
     * Find part books.
     *
     * @param recordsShown   the records shown
     * @param recordsPerPage the records per page
     * @return the list of found books
     * @throws ServiceException the service exception
     */
    List<Book> findPartOfAll(int recordsShown, int recordsPerPage) throws ServiceException;

    /**
     * Find books by name.
     *
     * @param searchName     the search value
     * @param recordsShown   the records shown
     * @param recordsPerPage the records per page
     * @return the list of found books
     * @throws ServiceException the service exception
     */
    List<Book> findByName(String searchName, int recordsShown, int recordsPerPage) throws ServiceException;

    /**
     * Find book by id.
     *
     * @param bookId the book id
     * @return the optional of found Book
     * @throws ServiceException the service exception
     */
    Optional<Book> findById(long bookId) throws ServiceException;

    /**
     * Check parameter is unique.
     *
     * @param title the book title
     * @param year  the book year
     * @param pages the book pages
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isParameterUnique(String title, String year, String pages) throws ServiceException;

    /**
     * Add new book.
     *
     * @param bookParameter the book parameter
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean add(Map<String, String> bookParameter) throws ServiceException;

    /**
     * Remove book.
     *
     * @param bookId the book id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean remove(String bookId) throws ServiceException;
}
