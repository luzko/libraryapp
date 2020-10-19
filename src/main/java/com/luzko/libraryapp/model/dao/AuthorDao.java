package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.Author;

/**
 * The interface Author dao.
 */
public interface AuthorDao extends BaseDao<Author> {
    /**
     * Add.
     *
     * @param name the name
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(String name) throws DaoException;

    /**
     * Check name is unique.
     *
     * @param name the name
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isNameUnique(String name) throws DaoException;
}
