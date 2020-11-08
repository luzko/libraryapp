package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.Author;

import java.util.List;

/**
 * The interface represents Author DAO.
 */
public interface AuthorDao extends BaseDao {

    /**
     * Find all authors.
     *
     * @return the list of found authors
     * @throws DaoException the dao exception
     */
    List<Author> findAll() throws DaoException;

    /**
     * Add new author.
     *
     * @param name the author's name
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(String name) throws DaoException;

    /**
     * Check name is unique.
     *
     * @param name the author's name
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isNameUnique(String name) throws DaoException;
}
