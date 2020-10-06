package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.book.Book;
import com.luzko.libraryapp.service.book.BookService;
import com.luzko.libraryapp.service.book.impl.BookServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LibraryPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LibraryPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        BookService bookService = BookServiceImpl.getInstance();

        try {
            List<Book> books = bookService.findAll();
            System.out.println(books);
            request.getSession().setAttribute(RequestParameter.ALL_BOOKS, books);
            router.setPagePath(PagePath.LIBRARY);
            router.setRouterType(RouterType.FORWARD);
            return router;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in login", e);
            /*request.setAttribute(RequestParameter.ERROR_MESSAGE, e); //TODO e?
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);*/
            //TODO
        }
        return router;
    }
}
