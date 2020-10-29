package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.entity.Book;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.model.service.BookService;
import com.luzko.libraryapp.model.service.OrderService;
import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class OrderPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OrderPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        String orderType = request.getParameter(RequestParameter.ORDER_TYPE);
        request.getSession().setAttribute(RequestParameter.ORDER_TYPE, orderType);
        try {
            switch (orderType) {
                case RequestParameter.USER_ORDER -> userOrderOverview(router, orderService, request);
                case RequestParameter.BOOK_ORDER -> bookOrderOverview(router, orderService, request);
                case RequestParameter.NEW_ORDER -> newOrderOverview(router, orderService, request);
                case RequestParameter.ALL_ORDER -> allOrderOverview(router, orderService, request);
                default -> router.setPagePath(PagePath.ERROR);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in order page", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }

    private void userOrderOverview(Router router, OrderService orderService, HttpServletRequest request) throws ServiceException {
        Object userIdObject = request.getSession().getAttribute(RequestParameter.USER_ID);
        long userId = (long) userIdObject;
        int countRecords = orderService.findCount(userId, RequestParameter.USER_ORDER);
        if (countRecords > 0) {
            int shownRecords = shownRecordsPagination(countRecords, request);
            List<Order> orderList = orderService.findPart(userId, RequestParameter.USER_ORDER, shownRecords, RECORDS_PER_PAGE);
            defineOrdersAttribute(router, orderList, request);
        } else {
            request.setAttribute(RequestParameter.NOT_FOUND_ORDERS,
                    ConfigurationManager.getMessageProperty(RequestParameter.PATH_ORDER_NOT_FOUND));
            router.setPagePath(PagePath.USER);
        }
    }

    private void bookOrderOverview(Router router, OrderService orderService, HttpServletRequest request) throws ServiceException {
        String bookIdString = request.getParameter(RequestParameter.BOOK_ID);
        long bookId = Long.parseLong(bookIdString);
        int countRecords = orderService.findCount(bookId, RequestParameter.BOOK_ORDER);
        if (countRecords > 0) {
            int shownRecords = shownRecordsPagination(countRecords, request);
            List<Order> orderList = orderService.findPart(bookId, RequestParameter.BOOK_ORDER, shownRecords, RECORDS_PER_PAGE);
            defineOrdersAttribute(router, orderList, request);
        } else {
            request.setAttribute(RequestParameter.NOT_FOUND_ORDERS,
                    ConfigurationManager.getMessageProperty(RequestParameter.PATH_ORDER_NOT_FOUND));
            BookService bookService = ServiceFactory.getInstance().getBookService();
            Optional<Book> bookOptional = bookService.findById(bookId);
            Book book = bookOptional.get();
            request.getSession().setAttribute(RequestParameter.BOOK, book);
            router.setPagePath(PagePath.BOOK_OVERVIEW);
        }
    }

    private void newOrderOverview(Router router, OrderService orderService, HttpServletRequest request) throws ServiceException {
        int countRecords = orderService.findCount(RequestParameter.NEW_ORDER);
        if (countRecords > 0) {
            int shownRecords = shownRecordsPagination(countRecords, request);
            List<Order> orderList = orderService.findPart(RequestParameter.NEW_ORDER, shownRecords, RECORDS_PER_PAGE);
            defineOrdersAttribute(router, orderList, request);
        } else {
            request.setAttribute(RequestParameter.NOT_FOUND_ORDERS,
                    ConfigurationManager.getMessageProperty(RequestParameter.PATH_ORDER_NOT_FOUND));
            router.setPagePath(PagePath.USER);
        }
    }

    private void allOrderOverview(Router router, OrderService orderService, HttpServletRequest request) throws ServiceException {
        int countRecords = orderService.findCount(RequestParameter.ALL_ORDER);
        if (countRecords > 0) {
            int shownRecords = shownRecordsPagination(countRecords, request);
            List<Order> orderList = orderService.findPart(RequestParameter.ALL_ORDER, shownRecords, RECORDS_PER_PAGE);
            defineOrdersAttribute(router, orderList, request);
        }
    }

    private void defineOrdersAttribute(Router router, List<Order> orderList, HttpServletRequest request) {
        request.getSession().setAttribute(RequestParameter.ALL_ORDERS, orderList);
        router.setPagePath(PagePath.ORDERS);
    }
}
