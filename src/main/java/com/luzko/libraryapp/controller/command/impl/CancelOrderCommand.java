package com.luzko.libraryapp.controller.command.impl;

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
                request.getSession().setAttribute(RequestParameter.ALL_ORDERS, orderList);
                request.getSession().setAttribute(RequestParameter.ORDER_TYPE, orderType);
                router.setPagePath(PagePath.ORDERS);
                router.setRouterType(RouterType.REDIRECT);
            } else {
                router.setPagePath(PagePath.ERROR);
                router.setRouterType(RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in cancel order", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }

    private List<Order> defineOrderList(OrderService orderService, HttpServletRequest request) throws ServiceException {
        Object userIdObject = request.getSession().getAttribute(RequestParameter.USER_ID);
        long userId = (long) userIdObject;
        String currentPageString = request.getParameter(RequestParameter.CURRENT_PAGE);
        int currentPage = currentPageString != null ? Integer.parseInt(currentPageString) : 1;
        int recordsPerPage = Integer.parseInt(RequestParameter.RECORD_PAGE);
        List<Order> orderList = orderService.findByUserId(userId);
        definePagination(request, orderList.size(), currentPage, recordsPerPage);
        int recordsView = (currentPage - 1) * recordsPerPage;
        return orderList.subList(recordsView, Math.min(recordsView + recordsPerPage, orderList.size()));
    }
}
