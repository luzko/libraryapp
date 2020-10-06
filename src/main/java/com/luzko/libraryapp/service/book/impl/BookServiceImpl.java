package com.luzko.libraryapp.service.book.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.dao.book.BookDao;
import com.luzko.libraryapp.model.dao.book.impl.BookDaoImpl;
import com.luzko.libraryapp.model.entity.book.Book;
import com.luzko.libraryapp.service.book.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private static final BookServiceImpl instance = new BookServiceImpl();
    //TODO add logger..

    private BookServiceImpl() {

    }

    public static BookServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Book> findAll() throws ServiceException {
        BookDao bookDao = BookDaoImpl.getInstance();
        try {
            return bookDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("service", e);
        }
    }
}
