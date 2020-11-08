package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.AttributeValue;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.OrderService;
import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type represents the command for creating a book order.
 */
public class CreateOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateOrderCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        String bookId = request.getParameter(RequestParameter.BOOK_ID);
        String typeOrder = request.getParameter(RequestParameter.CREATE_ORDER_TYPE);
        String locale = (String) request.getSession().getAttribute(AttributeName.LOCALE);
        long userId = (long) request.getSession().getAttribute(AttributeName.USER_ID);
        try {
            if (orderService.isCreateOrder(userId, bookId, typeOrder)) {
                String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_ORDER_SUCCESS, locale);
                request.getSession().setAttribute(AttributeName.ORDER_SUCCESS, attributeValue);
            } else {
                String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_ORDER_ERROR, locale);
                request.getSession().setAttribute(AttributeName.ORDER_ERROR, attributeValue);
            }
            router.setPagePath(PagePath.BOOK_OVERVIEW);
            router.setRedirect();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error create order", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }
}
