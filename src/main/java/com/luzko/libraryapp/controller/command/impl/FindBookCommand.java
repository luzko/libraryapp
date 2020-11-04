package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.AttributeValue;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Book;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.BookService;
import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

public class FindBookCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindBookCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String searchName = request.getParameter(RequestParameter.SEARCH);
        try {
            if (searchName.isBlank()) {
                request.getSession().removeAttribute(AttributeName.SEARCH);
                List<Book> bookList = defineBookList(request);
                request.getSession().setAttribute(AttributeName.ALL_BOOKS, bookList);
            } else {
                findBook(request, searchName);
            }
            router.setPagePath(PagePath.LIBRARY);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in find book", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }

    private void findBook(HttpServletRequest request, String searchName) throws ServiceException {
        request.getSession().setAttribute(AttributeName.SEARCH, searchName);
        List<Book> bookList = defineSearchBookList(searchName, request);
        if (bookList.isEmpty()) {
            String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_BOOK_NOT_FOUND,
                    (String) request.getSession().getAttribute(AttributeName.LOCALE));
            request.setAttribute(AttributeName.NOT_FOUND_BOOKS, attributeValue);
        } else {
            request.getSession().setAttribute(AttributeName.ALL_BOOKS, bookList);
        }
    }

    private List<Book> defineBookList(HttpServletRequest request) throws ServiceException {
        BookService bookService = ServiceFactory.getInstance().getBookService();
        int countRecords = bookService.findCountRecords();
        int shownRecords = shownRecordsPagination(countRecords, request);
        return bookService.findPartOfAll(shownRecords, RECORDS_PER_PAGE);
    }

    private List<Book> defineSearchBookList(String searchName, HttpServletRequest request) throws ServiceException {
        BookService bookService = ServiceFactory.getInstance().getBookService();
        List<Book> bookList = Collections.emptyList();
        int countRecords = bookService.findCountRecords(searchName);
        if (countRecords > 0) {
            int shownRecords = shownRecordsPagination(countRecords, request);
            bookList = bookService.findByName(searchName, shownRecords, RECORDS_PER_PAGE);
        }
        return bookList;
    }
}
