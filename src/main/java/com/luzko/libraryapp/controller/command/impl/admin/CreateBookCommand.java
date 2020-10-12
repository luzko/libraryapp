package com.luzko.libraryapp.controller.command.impl.admin;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.service.BookService;
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
        BookService bookService = ServiceFactory.getInstance().getBookService();
        Map<String, String> bookParameter = fillBookParameter(request);
        try {
            if (bookService.isParameterUnique(
                    bookParameter.get(ColumnName.TITLE), bookParameter.get(ColumnName.YEAR), bookParameter.get(ColumnName.PAGES))
            ) {
                if (bookService.add(bookParameter)) {
                    request.getSession().setAttribute(RequestParameter.CORRECT_DATA_MESSAGE,
                            ConfigurationManager.getMessageProperty(RequestParameter.PATH_BOOK_CORRECT));
                    request.getSession().setAttribute(RequestParameter.ERROR_DATA_MESSAGE, RequestParameter.EMPTY);
                } else {
                    request.getSession().setAttribute(RequestParameter.ERROR_DATA_MESSAGE,
                            ConfigurationManager.getMessageProperty(RequestParameter.PATH_BOOK_DATA));
                    request.getSession().setAttribute(RequestParameter.CORRECT_DATA_MESSAGE, RequestParameter.EMPTY);
                    request.setAttribute(RequestParameter.BOOK_PARAMETER, bookParameter);
                }
                request.getSession().setAttribute(RequestParameter.CREATE_TYPE, createType);
            } else {
                request.setAttribute(RequestParameter.ERROR_DATA_MESSAGE,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_LOGIN_EXIST));
                request.setAttribute(RequestParameter.BOOK_PARAMETER, bookParameter);
            }
            router.setPagePath(PagePath.CREATE_BOOK);
            router.setRouterType(RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Create book command error", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.REDIRECT);
        }
        return router;
    }

    private Map<String, String> fillBookParameter(HttpServletRequest request) {
        Map<String, String> bookParameter = new HashMap<>();
        bookParameter.put(ColumnName.TITLE, request.getParameter(ColumnName.TITLE));
        bookParameter.put(ColumnName.YEAR, request.getParameter(ColumnName.YEAR));
        bookParameter.put(ColumnName.PAGES, request.getParameter(ColumnName.PAGES));
        bookParameter.put(ColumnName.NUMBER_COPIES, request.getParameter(ColumnName.NUMBER_COPIES));
        bookParameter.put(ColumnName.CATEGORY, request.getParameter(ColumnName.CATEGORY));
        bookParameter.put(ColumnName.AUTHOR, request.getParameter(ColumnName.AUTHOR));
        bookParameter.put(ColumnName.DESCRIPTION, request.getParameter(ColumnName.DESCRIPTION));
        return bookParameter;
    }
}
