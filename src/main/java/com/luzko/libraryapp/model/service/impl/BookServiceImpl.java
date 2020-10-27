package com.luzko.libraryapp.model.service.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.builder.BookBuilder;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.BookDao;
import com.luzko.libraryapp.model.dao.impl.BookDaoImpl;
import com.luzko.libraryapp.model.entity.Book;
import com.luzko.libraryapp.model.entity.Category;
import com.luzko.libraryapp.model.service.BookService;
import com.luzko.libraryapp.validator.BookValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The type Book service.
 */
public class BookServiceImpl implements BookService {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

    @Override
    public Optional<Book> findById(String bookId) throws ServiceException {
        logger.log(Level.INFO, "Find by id execute: {}", bookId);
        BookDao bookDao = BookDaoImpl.getInstance();
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
        BookDao bookDao = BookDaoImpl.getInstance();
        try {
            return bookDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Find all error", e);
        }
    }

    @Override
    public boolean isParameterUnique(String title, String yearValue, String pagesValue) throws ServiceException {
        logger.log(Level.INFO, "Check unique parameter execute");
        boolean isParameterUnique = false;
        int year = Integer.parseInt(yearValue);
        int pages = Integer.parseInt(pagesValue);
        if (BookValidator.isValidTitle(title) && BookValidator.isYearValid(year) && BookValidator.isPagesValid(pages)) {
            BookDao bookDao = BookDaoImpl.getInstance();
            try {
                isParameterUnique = bookDao.isParameterUnique(title, year, pages);
            } catch (DaoException e) {
                throw new ServiceException("Login unique error", e);
            }
        }
        return isParameterUnique;
    }

    @Override
    public boolean add(Map<String, String> bookParameter) throws ServiceException {
        logger.log(Level.INFO, "Add book execute");
        boolean isBookAdd = false;
        BookDao bookDao = BookDaoImpl.getInstance();
        if (BookValidator.isValidBookParameter(bookParameter)) {
            try {
                String title = bookParameter.get(ColumnName.TITLE);
                String year = bookParameter.get(ColumnName.YEAR);
                String pages = bookParameter.get(ColumnName.PAGES);
                String number = bookParameter.get(ColumnName.NUMBER_COPIES);
                String category = bookParameter.get(ColumnName.CATEGORY);
                String description = bookParameter.get(ColumnName.DESCRIPTION);
                long authorId = Long.parseLong(bookParameter.get(ColumnName.AUTHOR));
                BookBuilder bookBuilder = new BookBuilder()
                        .setTitle(title)
                        .setYear(Integer.parseInt(year))
                        .setPage(Integer.parseInt(pages))
                        .setNumberCopy(Integer.parseInt(number))
                        .setCategory(Category.valueOf(category))
                        .setDescription(description);
                Book book = new Book(bookBuilder);
                isBookAdd = bookDao.add(book, authorId);
            } catch (DaoException e) {
                throw new ServiceException("Add book error", e);
            }
        }
        return isBookAdd;
    }
}