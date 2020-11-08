package com.luzko.libraryapp.model.service.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.dao.OrderDao;
import com.luzko.libraryapp.model.dao.impl.OrderDaoImpl;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.model.entity.OrderType;
import com.luzko.libraryapp.model.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type represents Order Service implementation.
 */
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    public int findCount(String typeOrder) throws ServiceException {
        logger.log(Level.INFO, "Find count orders");
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            return orderDao.findCount(typeOrder);
        } catch (DaoException e) {
            throw new ServiceException("Find count orders error");
        }
    }

    public int findCount(long paramId, String typeOrder) throws ServiceException {
        logger.log(Level.INFO, "Find count order");
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            return orderDao.findCount(paramId, typeOrder);
        } catch (DaoException e) {
            throw new ServiceException("Find count orders error");
        }
    }

    public List<Order> findPart(String typeOrder, int shownRecords, int recordsPerPage) throws ServiceException {
        logger.log(Level.INFO, "Find orders execute");
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            return orderDao.findPart(typeOrder, shownRecords, recordsPerPage);
        } catch (DaoException e) {
            throw new ServiceException("Find orders error", e);
        }
    }

    public List<Order> findPart(long paramId, String typeOrder, int shownRecords, int recordsPerPage) throws ServiceException {
        logger.log(Level.INFO, "Find orders execute");
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            return orderDao.findPart(paramId, typeOrder, shownRecords, recordsPerPage);
        } catch (DaoException e) {
            throw new ServiceException("Find orders error", e);
        }
    }

    @Override
    public boolean isCreateOrder(long userId, String bookIdString, String orderTypeString) throws ServiceException {
        logger.log(Level.INFO, "Create order execute");
        OrderDao orderDao = OrderDaoImpl.getInstance();
        long bookId = Long.parseLong(bookIdString);
        try {
            OrderType orderType = OrderType.valueOf(orderTypeString.toUpperCase());
            return orderDao.isCreateOrder(userId, bookId, orderType);
        } catch (DaoException e) {
            throw new ServiceException("Create order error", e);
        }
    }

    @Override
    public boolean isCancel(String orderIdString) throws ServiceException {
        logger.log(Level.INFO, "Cancel order execute: {}", orderIdString);
        OrderDao orderDao = OrderDaoImpl.getInstance();
        long orderId = Long.parseLong(orderIdString);
        try {
            return orderDao.isCancel(orderId);
        } catch (DaoException e) {
            throw new ServiceException("Cancel order error", e);
        }
    }

    @Override
    public boolean isReturn(String orderIdString, String bookIdString) throws ServiceException {
        logger.log(Level.INFO, "Return order execute: {}", orderIdString);
        OrderDao orderDao = OrderDaoImpl.getInstance();
        long orderId = Long.parseLong(orderIdString);
        long bookId = Long.parseLong(bookIdString);
        try {
            return orderDao.isReturn(orderId, bookId);
        } catch (DaoException e) {
            throw new ServiceException("Return order error", e);
        }
    }

    @Override
    public boolean isDeny(String orderIdString) throws ServiceException {
        logger.log(Level.INFO, "Deny order execute: {}", orderIdString);
        OrderDao orderDao = OrderDaoImpl.getInstance();
        long orderId = Long.parseLong(orderIdString);
        try {
            return orderDao.isDeny(orderId);
        } catch (DaoException e) {
            throw new ServiceException("Return order error", e);
        }
    }

    @Override
    public boolean isApprove(String orderIdString, String bookIdString, String userIdString) throws ServiceException {
        logger.log(Level.INFO, "Approve order execute");
        OrderDao orderDao = OrderDaoImpl.getInstance();
        long orderId = Long.parseLong(orderIdString);
        long bookId = Long.parseLong(bookIdString);
        long userId = Long.parseLong(userIdString);
        try {
            return orderDao.isApprove(orderId, bookId, userId);
        } catch (DaoException e) {
            throw new ServiceException("Approve order error", e);
        }
    }
}