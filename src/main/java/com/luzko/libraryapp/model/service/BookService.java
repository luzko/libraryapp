package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Book service.
 */
public interface BookService {
    /**
     * Find count records.
     *
     * @return the int
     * @throws ServiceException the service exception
     */
    int findCountRecords() throws ServiceException;

    int findCountRecords(String searchName) throws ServiceException;

    /**
     * Find part.
     *
     * @param recordsShown   the records shown
     * @param recordsPerPage the records per page
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Book> findPartOfAll(int recordsShown, int recordsPerPage) throws ServiceException;

    List<Book> findByName(String searchName, int recordsShown, int recordsPerPage) throws ServiceException;

    /**
     * Find by id.
     *
     * @param bookId the book id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Book> findById(long bookId) throws ServiceException;

    /**
     * Check parameter is unique.
     *
     * @param title the title
     * @param year  the year
     * @param pages the pages
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isParameterUnique(String title, String year, String pages) throws ServiceException;

    /**
     * Add.
     *
     * @param bookParameter the book parameter
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean add(Map<String, String> bookParameter) throws ServiceException;

    boolean remove(String bookId) throws ServiceException;
}
