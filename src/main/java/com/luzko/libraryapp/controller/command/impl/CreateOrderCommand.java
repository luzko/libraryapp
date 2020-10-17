package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.service.OrderService;
import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateOrderCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        String bookId = request.getParameter(RequestParameter.BOOK_ID);
        String typeOrder = request.getParameter(RequestParameter.CREATE_ORDER_TYPE);
        Object userIdObject = request.getSession().getAttribute(RequestParameter.USER_ID);
        long userId = (long) userIdObject;
        try {
            if (orderService.isCreateOrder(userId, bookId, typeOrder)) {
                request.getSession().setAttribute(RequestParameter.ORDER_ERROR, RequestParameter.EMPTY);
                request.getSession().setAttribute(RequestParameter.ORDER_SUCCESS,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_ORDER_SUCCESS));
            } else {
                request.getSession().setAttribute(RequestParameter.ORDER_SUCCESS, RequestParameter.EMPTY);
                request.getSession().setAttribute(RequestParameter.ORDER_ERROR,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_ORDER_ERROR));
            }
            router.setPagePath(PagePath.BOOK_OVERVIEW);
            router.setRouterType(RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error create order", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }
}
