package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Author;

import java.util.List;

/**
 * he interface represents Author Service.
 */
public interface AuthorService {
    /**
     * Find all authors.
     *
     * @return the list of found authors
     * @throws ServiceException the service exception
     */
    List<Author> findAll() throws ServiceException;

    /**
     * Add new author.
     *
     * @param name the author's name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean add(String name) throws ServiceException;
}
