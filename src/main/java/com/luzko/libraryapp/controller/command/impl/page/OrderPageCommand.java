package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.AttributeValue;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Book;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.model.factory.ServiceFactory;
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
        request.getSession().setAttribute(AttributeName.ORDER_TYPE, orderType);
        try {
            switch (orderType) {
                case AttributeName.USER_ORDER -> userOrderOverview(router, orderService, request);
                case AttributeName.BOOK_ORDER -> bookOrderOverview(router, orderService, request);
                case AttributeName.NEW_ORDER -> newOrderOverview(router, orderService, request);
                case AttributeName.ALL_ORDER -> allOrderOverview(router, orderService, request);
                default -> router.setPagePath(PagePath.ERROR);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in order page", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }

    private void userOrderOverview(Router router, OrderService orderService, HttpServletRequest request) throws ServiceException {
        long userId = (long) request.getSession().getAttribute(AttributeName.USER_ID);
        int countRecords = orderService.findCount(userId, AttributeName.USER_ORDER);
        if (countRecords > 0) {
            int shownRecords = shownRecordsPagination(countRecords, request);
            List<Order> orderList = orderService.findPart(userId, AttributeName.USER_ORDER, shownRecords, RECORDS_PER_PAGE);
            defineOrdersAttribute(router, orderList, request);
        } else {
            String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_ORDER_NOT_FOUND,
                    (String) request.getSession().getAttribute(AttributeName.LOCALE));
            request.setAttribute(AttributeName.NOT_FOUND_ORDERS, attributeValue);
            router.setPagePath(PagePath.USER);
        }
    }

    private void bookOrderOverview(Router router, OrderService orderService, HttpServletRequest request) throws ServiceException {
        String bookIdString = request.getParameter(RequestParameter.BOOK_ID);
        long bookId = Long.parseLong(bookIdString);
        int countRecords = orderService.findCount(bookId, AttributeName.BOOK_ORDER);
        if (countRecords > 0) {
            int shownRecords = shownRecordsPagination(countRecords, request);
            List<Order> orderList = orderService.findPart(bookId, AttributeName.BOOK_ORDER, shownRecords, RECORDS_PER_PAGE);
            defineOrdersAttribute(router, orderList, request);
        } else {
            bookOverview(router, bookId, request);
        }
    }

    private void newOrderOverview(Router router, OrderService orderService, HttpServletRequest request) throws ServiceException {
        int countRecords = orderService.findCount(AttributeName.NEW_ORDER);
        if (countRecords > 0) {
            int shownRecords = shownRecordsPagination(countRecords, request);
            List<Order> orderList = orderService.findPart(AttributeName.NEW_ORDER, shownRecords, RECORDS_PER_PAGE);
            defineOrdersAttribute(router, orderList, request);
        } else {
            String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_ORDER_NOT_FOUND,
                    (String) request.getSession().getAttribute(AttributeName.LOCALE));
            request.setAttribute(AttributeName.NOT_FOUND_ORDERS, attributeValue);
            router.setPagePath(PagePath.USER);
        }
    }

    private void allOrderOverview(Router router, OrderService orderService, HttpServletRequest request) throws ServiceException {
        int countRecords = orderService.findCount(AttributeName.ALL_ORDER);
        if (countRecords > 0) {
            int shownRecords = shownRecordsPagination(countRecords, request);
            List<Order> orderList = orderService.findPart(AttributeName.ALL_ORDER, shownRecords, RECORDS_PER_PAGE);
            defineOrdersAttribute(router, orderList, request);
        }
    }

    private void defineOrdersAttribute(Router router, List<Order> orderList, HttpServletRequest request) {
        request.getSession().setAttribute(AttributeName.ALL_ORDERS, orderList);
        router.setPagePath(PagePath.ORDERS);
    }

    private void bookOverview(Router router, long bookId, HttpServletRequest request) throws ServiceException {
        String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_ORDER_NOT_FOUND,
                (String) request.getSession().getAttribute(AttributeName.LOCALE));
        request.setAttribute(AttributeName.NOT_FOUND_ORDERS, attributeValue);
        BookService bookService = ServiceFactory.getInstance().getBookService();
        Optional<Book> bookOptional = bookService.findById(bookId);
        Book book = bookOptional.get();
        request.getSession().setAttribute(AttributeName.BOOK, book);
        router.setPagePath(PagePath.BOOK_OVERVIEW);
    }
}
