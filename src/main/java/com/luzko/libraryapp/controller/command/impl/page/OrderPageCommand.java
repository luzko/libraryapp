package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OrderPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        String orderType = request.getParameter(RequestParameter.ORDER_TYPE);
        request.setAttribute(RequestParameter.ORDER_TYPE, orderType);
        try {
            switch (orderType) {
                case RequestParameter.USER_ORDER -> userOrderOverview(router, orderService, request);
                case RequestParameter.BOOK_ORDER -> bookOrderOverview(router, orderService, request);
                case RequestParameter.NEW_ORDER -> newOrderOverview(router, orderService, request);
                case RequestParameter.ALL_ORDER -> allOrderOverview(router, orderService, request);
                default -> {
                    router.setPagePath(PagePath.ERROR);
                    router.setRouterType(RouterType.FORWARD);
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in order page", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }

    private void userOrderOverview(Router router, OrderService orderService, HttpServletRequest request) throws ServiceException {
        Object userIdObject = request.getSession().getAttribute(RequestParameter.USER_ID);
        long userId = (long) userIdObject;
        List<Order> orders = orderService.findByUserId(userId);
        request.getSession().setAttribute(RequestParameter.ALL_ORDERS, orders);
        router.setPagePath(PagePath.ORDERS);
        router.setRouterType(RouterType.FORWARD);
    }

    private void bookOrderOverview(Router router, OrderService orderService, HttpServletRequest request) throws ServiceException {
        String bookId = request.getParameter(RequestParameter.BOOK_ID);
        List<Order> orders = orderService.findByBookId(bookId);
        System.out.println(orders); //TODO
        request.getSession().setAttribute(RequestParameter.ALL_ORDERS, orders);
        router.setPagePath(PagePath.ORDERS);
        router.setRouterType(RouterType.FORWARD);
    }

    private void newOrderOverview(Router router, OrderService orderService, HttpServletRequest request) throws ServiceException {

    }

    private void allOrderOverview(Router router, OrderService orderService, HttpServletRequest request) throws ServiceException {

    }
}
