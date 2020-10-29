package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.BookService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RemoveBookCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RemoveBookCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        BookService bookService = ServiceFactory.getInstance().getBookService();
        String bookId = request.getParameter(RequestParameter.BOOK_ID);
        try {
            if (bookService.remove(bookId)) {
                request.getSession().setAttribute("", ""); //TODO TODO TODO
                router.setPagePath(PagePath.BOOK_OVERVIEW);
                router.setRedirect();
            } else {
                router.setPagePath(PagePath.ERROR);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Remove book command error", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }
}
