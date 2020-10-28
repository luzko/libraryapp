package com.luzko.libraryapp.model.service.impl;

import com.luzko.libraryapp.controller.RequestParameter;
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
 * The type Order service.
 */
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    @Override
    public List<Order> findPartByUserId(Object userIdObject, int shownRecords, int recordsPerPage) throws ServiceException {
        logger.log(Level.INFO, "Find by user id execute: {}", userIdObject);
        OrderDao orderDao = OrderDaoImpl.getInstance();
        long userId = (long) userIdObject;
        try {
            return orderDao.findPartByUserId(userId, shownRecords, recordsPerPage);
        } catch (DaoException e) {
            throw new ServiceException("Find by user id error", e);
        }
    }

    @Override
    public List<Order> findPartByBookId(String bookIdString, int shownRecords, int recordsPerPage) throws ServiceException {
        logger.log(Level.INFO, "Find by book id execute: {}", bookIdString);
        OrderDao orderDao = OrderDaoImpl.getInstance();
        long bookId = Long.parseLong(bookIdString);
        try {
            return orderDao.findPartByBookId(bookId, shownRecords, recordsPerPage);
        } catch (DaoException e) {
            throw new ServiceException("Find by book id error", e);
        }
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        logger.log(Level.INFO, "Find all orders execute");
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Find all error", e);
        }
    }

    @Override
    public List<Order> findPartNew(int shownRecords, int recordsPerPage) throws ServiceException {
        logger.log(Level.INFO, "Find new orders execute");
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            return orderDao.findPartNew(shownRecords, recordsPerPage);
        } catch (DaoException e) {
            throw new ServiceException("Find new error", e);
        }
    }

    @Override
    public List<Order> findPartOfAll(int shownRecords, int recordsPerPage) throws ServiceException {
        logger.log(Level.INFO, "Find all orders execute");
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            return orderDao.findPartOfAll(shownRecords, recordsPerPage);
        } catch (DaoException e) {
            throw new ServiceException("Find all error", e);
        }
    }

    @Override
    public boolean isCreateOrder(long userId, String bookIdString, String orderTypeString) throws ServiceException {
        logger.log(Level.INFO, "Create order execute");
        OrderDao orderDao = OrderDaoImpl.getInstance();
        long bookId = Long.parseLong(bookIdString);
        try {
            OrderType orderType = orderTypeString.equals(RequestParameter.TYPE_HOME) ?
                    OrderType.HOME : OrderType.READING_ROOM;
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

    @Override
    public int findCountByUserId(Object userIdObject) throws ServiceException {
        logger.log(Level.INFO, "Find count order by user");
        OrderDao orderDao = OrderDaoImpl.getInstance();
        long userId = (long) userIdObject;
        try {
            return orderDao.findCountByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Find count order by user error");
        }
    }

    @Override
    public int findCountByBookId(String bookIdString) throws ServiceException {
        OrderDao orderDao = OrderDaoImpl.getInstance();
        long bookId = Long.parseLong(bookIdString);
        try {
            return orderDao.findCountByBookId(bookId);
        } catch (DaoException e) {
            throw new ServiceException("Find count order by book error");
        }
    }

    @Override
    public int findCountNew() throws ServiceException {
        logger.log(Level.INFO, "Find count new order");
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            return orderDao.findCountNew();
        } catch (DaoException e) {
            throw new ServiceException("Find count new order error");
        }
    }

    @Override
    public int findCountAll() throws ServiceException {
        logger.log(Level.INFO, "Find count all order");
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            return orderDao.findCountAll();
        } catch (DaoException e) {
            throw new ServiceException("Find count all order error");
        }
    }
}