package com.luzko.libraryapp.model.dao.author;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.dao.BaseDao;
import com.luzko.libraryapp.model.entity.author.Author;

public interface AuthorDao extends BaseDao<Author> {
    boolean add(String name) throws DaoException;

    boolean isNameUnique(String name) throws DaoException;
}
