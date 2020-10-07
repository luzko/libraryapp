package com.luzko.libraryapp.service.book.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.DaoFactory;
import com.luzko.libraryapp.model.dao.book.BookDao;
import com.luzko.libraryapp.model.entity.book.Book;
import com.luzko.libraryapp.service.book.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    @Override
    public List<Book> findAll() throws ServiceException {
        BookDao bookDao = DaoFactory.getInstance().getBookDao();
        try {
            return bookDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Find all error", e);
        }
    }
}
