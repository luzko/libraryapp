package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Author;

import java.util.List;
import java.util.Optional;

/**
 * The interface Author service.
 */
public interface AuthorService {
    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Author> findById(long id) throws ServiceException;

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
