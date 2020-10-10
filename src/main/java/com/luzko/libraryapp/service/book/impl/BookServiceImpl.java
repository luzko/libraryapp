package com.luzko.libraryapp.service.book.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.DaoFactory;
import com.luzko.libraryapp.model.dao.book.BookDao;
import com.luzko.libraryapp.model.entity.book.Book;
import com.luzko.libraryapp.service.book.BookService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

    @Override
    public Optional<Book> findById(String bookId) throws ServiceException {
        logger.log(Level.INFO, "Find by id execute: {}", bookId);
        BookDao bookDao = DaoFactory.getInstance().getBookDao();
        try {
            long id = Long.parseLong(bookId);
            return bookDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Find by id error", e);
        }
    }

    @Override
    public List<Book> findAll() throws ServiceException {
        logger.log(Level.INFO, "Find all execute");
        BookDao bookDao = DaoFactory.getInstance().getBookDao();
        try {
            return bookDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Find all error", e);
        }
    }
}
