package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.model.entity.Book;
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
        Router router = new Router();
        try {
            List<Book> bookList = defineBookList(request);
            request.getSession().setAttribute(RequestParameter.ALL_BOOKS, bookList);
            router.setPagePath(PagePath.LIBRARY);
            router.setRouterType(RouterType.FORWARD);
            return router;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in library page", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }

    private List<Book> defineBookList(HttpServletRequest request) throws ServiceException {
        BookService bookService = ServiceFactory.getInstance().getBookService();
        String currentPageString = request.getParameter(RequestParameter.CURRENT_PAGE);
        int currentPage = currentPageString != null ? Integer.parseInt(currentPageString) : 1;
        int recordsPerPage = Integer.parseInt(RequestParameter.RECORD_PAGE);
        List<Book> bookList = bookService.findAll();
        definePagination(request, bookList.size(), currentPage, recordsPerPage);
        int recordsView = (currentPage - 1) * recordsPerPage;
        return bookList.subList(recordsView, Math.min(recordsView + recordsPerPage, bookList.size()));
    }
}
