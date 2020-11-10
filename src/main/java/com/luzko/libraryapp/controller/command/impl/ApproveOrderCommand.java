package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.AttributeValue;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.OrderService;
import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The type represents the command for order approval.
 */
public class ApproveOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ApproveOrderCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        removeTempAttribute(request);
        Router router = new Router();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        String orderId = request.getParameter(RequestParameter.ORDER_ID);
        String bookId = request.getParameter(RequestParameter.BOOK_ID);
        String userId = request.getParameter(RequestParameter.USER_ID);
        try {
            if (!orderService.isApprove(orderId, bookId, userId)) {
                String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_NOT_APPROVE_USER,
                        (String) request.getSession().getAttribute(AttributeName.LOCALE));
                request.getSession().setAttribute(AttributeName.ERROR_APPROVE, attributeValue);
            }
            defineOrderType(router, orderService, request);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in deny order", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }

    private void defineOrderType(Router router, OrderService orderService, HttpServletRequest request) throws ServiceException {
        List<Order> orderList = defineOrderList(orderService, request);
        String orderType = request.getParameter(RequestParameter.ORDER_TYPE);
        request.getSession().setAttribute(AttributeName.ALL_ORDERS, orderList);
        request.getSession().setAttribute(AttributeName.ORDER_TYPE, orderType);
        router.setPagePath(PagePath.ORDERS);
        router.setRedirect();
    }

    private List<Order> defineOrderList(OrderService orderService, HttpServletRequest request) throws ServiceException {
        int countRecords = orderService.findCount(AttributeName.NEW_ORDER);
        int shownRecords = shownRecordsPagination(countRecords, request);
        return orderService.findPart(AttributeName.NEW_ORDER, shownRecords, RECORDS_PER_PAGE);
    }
}
