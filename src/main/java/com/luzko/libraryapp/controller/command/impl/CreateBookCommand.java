package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.AttributeValue;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.BookService;
import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CreateBookCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateBookCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String createType = request.getParameter(RequestParameter.CREATE_TYPE);
        String locale = (String) request.getSession().getAttribute(AttributeName.LOCALE);
        BookService bookService = ServiceFactory.getInstance().getBookService();
        Map<String, String> bookParameter = fillBookParameter(request);
        try {
            if (bookService.isParameterUnique(bookParameter.get(RequestParameter.TITLE), bookParameter.get(RequestParameter.YEAR),
                    bookParameter.get(RequestParameter.PAGES))) {
                addBook(bookService, bookParameter, request);
                request.getSession().setAttribute(AttributeName.CREATE_TYPE, createType);
            } else {
                String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_LOGIN_EXIST, locale);
                request.getSession().setAttribute(AttributeName.ERROR_DATA_MESSAGE, attributeValue);
                request.getSession().setAttribute(AttributeName.BOOK_PARAMETER, bookParameter);
            }
            router.setPagePath(PagePath.CREATE_BOOK);
            router.setRedirect();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Create book command error", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }

    private Map<String, String> fillBookParameter(HttpServletRequest request) {
        Map<String, String> bookParameter = new HashMap<>();
        bookParameter.put(ColumnName.TITLE, request.getParameter(RequestParameter.TITLE));
        bookParameter.put(ColumnName.YEAR, request.getParameter(RequestParameter.YEAR));
        bookParameter.put(ColumnName.PAGES, request.getParameter(RequestParameter.PAGES));
        bookParameter.put(ColumnName.NUMBER_COPIES, request.getParameter(RequestParameter.NUMBER_COPIES));
        bookParameter.put(ColumnName.CATEGORY, request.getParameter(RequestParameter.CATEGORY));
        bookParameter.put(ColumnName.AUTHOR, request.getParameter(RequestParameter.AUTHOR));
        bookParameter.put(ColumnName.DESCRIPTION, request.getParameter(RequestParameter.DESCRIPTION));
        return bookParameter;
    }

    private void addBook(BookService bookService, Map<String, String> bookParameter, HttpServletRequest request) throws ServiceException {
        String locale = (String) request.getSession().getAttribute(AttributeName.LOCALE);
        if (bookService.add(bookParameter)) {
            String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_BOOK_CORRECT, locale);
            request.getSession().setAttribute(AttributeName.CORRECT_DATA_MESSAGE, attributeValue);
        } else {
            String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_BOOK_DATA, locale);
            request.getSession().setAttribute(AttributeName.ERROR_DATA_MESSAGE, attributeValue);
            request.setAttribute(AttributeName.BOOK_PARAMETER, bookParameter);
        }
    }
}
