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
import java.util.Optional;

public class BookOverviewCommand implements Command {
    private static final Logger logger = LogManager.getLogger(BookOverviewCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        BookService bookService = ServiceFactory.getInstance().getBookService();
        String bookIdString = request.getParameter(RequestParameter.BOOK_ID);
        long bookId = Long.parseLong(bookIdString);
        try {
            Optional<Book> bookOptional = bookService.findById(bookId);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                request.getSession().setAttribute(AttributeName.BOOK, book);
                router.setPagePath(PagePath.BOOK_OVERVIEW);
            } else {
                String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_BOOK_OVERVIEW,
                        (String) request.getSession().getAttribute(AttributeName.LOCALE));
                request.getSession().setAttribute(AttributeName.OVERVIEW_ERROR, attributeValue);
                router.setPagePath(PagePath.LIBRARY);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in library page", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }
}
