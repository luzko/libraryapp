package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.model.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CancelOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CancelOrderCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        String orderType = request.getParameter(RequestParameter.ORDER_TYPE);
        String orderId = request.getParameter(RequestParameter.ORDER_ID);
        try {
            if (orderService.isCancel(orderId)) {
                List<Order> orderList = defineOrderList(orderService, request);
                request.getSession().setAttribute(AttributeName.ALL_ORDERS, orderList);
                request.getSession().setAttribute(AttributeName.ORDER_TYPE, orderType);
                router.setPagePath(PagePath.ORDERS);
                router.setRedirect();
            } else {
                router.setPagePath(PagePath.ERROR);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in cancel order", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }

    private List<Order> defineOrderList(OrderService orderService, HttpServletRequest request) throws ServiceException {
        Object userIdObject = request.getSession().getAttribute(AttributeName.USER_ID);
        long userId = (long) userIdObject;
        int countRecords = orderService.findCount(userId, AttributeName.USER_ORDER);
        int shownRecords = shownRecordsPagination(countRecords, request);
        return orderService.findPart(userId, AttributeName.USER_ORDER, shownRecords, RECORDS_PER_PAGE);
    }
}
