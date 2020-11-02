package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Book;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.BookService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LibraryPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LibraryPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        removeTempAttribute(request);
        Router router = new Router();
        try {
            List<Book> bookList = defineBookList(request);
            request.getSession().setAttribute(AttributeName.ALL_BOOKS, bookList);
            router.setPagePath(PagePath.LIBRARY);
            return router;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in library page", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }

    private List<Book> defineBookList(HttpServletRequest request) throws ServiceException {
        BookService bookService = ServiceFactory.getInstance().getBookService();
        int countRecords = bookService.findCountRecords();
        int shownRecords = shownRecordsPagination(countRecords, request);
        return bookService.findPartOfAll(shownRecords, RECORDS_PER_PAGE);
    }
}