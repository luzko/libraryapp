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
        try {
            if (orderType.equals(RequestParameter.USER_ORDERS)) {
                Object userIdObject = request.getSession().getAttribute(RequestParameter.USER_ID);
                long userId = (long) userIdObject;
                List<Order> orders = orderService.findByUserId(userId);
                request.getSession().setAttribute(RequestParameter.ALL_ORDERS, orders);
                request.setAttribute(RequestParameter.ORDER_TYPE, orderType);
                router.setPagePath(PagePath.ORDERS);
                router.setRouterType(RouterType.FORWARD);
            } else if (orderType.equals(RequestParameter.BOOK_ORDERS)) {


                request.setAttribute(RequestParameter.ORDER_TYPE, orderType);
                //TODO
            } else {
                router.setPagePath(PagePath.ERROR);
                router.setRouterType(RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in order page", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }
}
