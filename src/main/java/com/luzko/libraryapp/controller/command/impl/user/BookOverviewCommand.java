package com.luzko.libraryapp.controller.command.impl.user;

import com.luzko.libraryapp.configuration.ConfigurationManager;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.model.entity.book.Book;
import com.luzko.libraryapp.service.book.BookService;
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
        String bookId = request.getParameter(RequestParameter.BOOK_ID);

        try {
            Optional<Book> bookOptional = bookService.findById(bookId);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                request.getSession().setAttribute(RequestParameter.TYPE_OVERVIEW_PAGE, RequestParameter.SEE_OVERVIEW_PAGE);
                request.getSession().setAttribute(RequestParameter.BOOK, book);
                router.setPagePath(PagePath.BOOK_OVERVIEW);
            } else {
                request.getSession().setAttribute(RequestParameter.OVERVIEW_ERROR,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_BOOK_OVERVIEW));
                router.setPagePath(PagePath.LIBRARY);
            }
            router.setRouterType(RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in library page", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }
}
