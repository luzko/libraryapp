package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.model.service.OrderService;
import com.luzko.libraryapp.model.service.UserService;
import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ApproveOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ApproveOrderCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        String orderType = request.getParameter(RequestParameter.ORDER_TYPE);
        String orderId = request.getParameter(RequestParameter.ORDER_ID);
        String bookId = request.getParameter(RequestParameter.BOOK_ID);
        String userId = request.getParameter(RequestParameter.USER_ID);
        try {
            if (orderService.isApprove(orderId, bookId, userId)) {
                request.getSession().setAttribute(RequestParameter.ERROR_APPROVE,
                        ConfigurationManager.getMessageProperty(RequestParameter.EMPTY));
            } else {
                request.getSession().setAttribute(RequestParameter.ERROR_APPROVE,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_NOT_APPROVE_USER));
            }
            List<Order> orderList = defineOrderList(orderService, request);
            request.getSession().setAttribute(RequestParameter.ALL_ORDERS, orderList);
            request.getSession().setAttribute(RequestParameter.ORDER_TYPE, orderType);
            router.setPagePath(PagePath.ORDERS);
            router.setRedirect();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in deny order", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }

    private List<Order> defineOrderList(OrderService orderService, HttpServletRequest request) throws ServiceException {
        int countRecords = orderService.findCountNew();
        int shownRecords = shownRecordsPagination(countRecords, request);
        return orderService.findPartNew(shownRecords, RECORDS_PER_PAGE);
    }
}
