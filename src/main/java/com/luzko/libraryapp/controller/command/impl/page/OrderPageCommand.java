package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class OrderPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OrderPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {

        Router router = new Router();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();


        String orderType = request.getParameter(RequestParameter.ORDER_TYPE);

        if (orderType.equals(RequestParameter.USER_ORDERS)) {
            Object userIdObject = request.getSession().getAttribute(RequestParameter.USER_ID);
            long userId = (long) userIdObject;

        }


        return null;
    }
}
