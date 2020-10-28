package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Author;

import java.util.List;

/**
 * The interface Author service.
 */
public interface AuthorService {
    /**
     * Find all.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Author> findAll() throws ServiceException;

    /**
     * Add.
     *
     * @param name the name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean add(String name) throws ServiceException;
}
