package com.luzko.libraryapp.controller.command.impl.reader;

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

public class ReturnOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ReturnOrderCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        String orderType = request.getParameter(RequestParameter.ORDER_TYPE);
        String orderId = request.getParameter(RequestParameter.ORDER_ID);
        try {
            if (orderService.isReturn(orderId)) {
                Object userIdObject = request.getSession().getAttribute(RequestParameter.USER_ID);
                long userId = (long) userIdObject;
                List<Order> orders = orderService.findByUserId(userId);
                request.getSession().setAttribute(RequestParameter.ALL_ORDERS, orders);
                request.setAttribute(RequestParameter.ORDER_TYPE, orderType);
                router.setPagePath(PagePath.ORDERS);
            } else {
                router.setPagePath(PagePath.ERROR);
            }
            router.setRouterType(RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in return order", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }
}