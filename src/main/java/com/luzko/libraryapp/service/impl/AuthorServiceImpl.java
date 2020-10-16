package com.luzko.libraryapp.service.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.DaoFactory;
import com.luzko.libraryapp.model.dao.AuthorDao;
import com.luzko.libraryapp.model.entity.Author;
import com.luzko.libraryapp.service.AuthorService;
import com.luzko.libraryapp.validator.BookValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService {
    private static final Logger logger = LogManager.getLogger(AuthorServiceImpl.class);

    @Override
    public boolean add(String name) throws ServiceException {
        logger.log(Level.INFO, "Add author execute: {}", name);
        boolean isAddAuthor = false;
        AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
        if (BookValidator.isValidAuthorName(name)) {
            try {
                if (authorDao.isNameUnique(name)) {
                    isAddAuthor = authorDao.add(name);
                }
            } catch (DaoException e) {
                throw new ServiceException("Add author error", e);
            }
        }
        return isAddAuthor;
    }

    @Override
    public Optional<Author> findById(long id) throws ServiceException {
        logger.log(Level.INFO, "Find by id execute: {}", id);
        AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
        Optional<Author> authorOptional;
        try {
            authorOptional = authorDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Find by id error", e);
        }
        return authorOptional;
    }

    @Override
    public List<Author> findAll() throws ServiceException {
        logger.log(Level.INFO, "Find all execute");
        AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
        try {
            return authorDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Find all error", e);
        }
    }
}