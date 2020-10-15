package com.luzko.libraryapp.service.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.DaoFactory;
import com.luzko.libraryapp.model.dao.OrderDao;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    @Override
    public List<Order> findByUserId(long userId) throws ServiceException {
        logger.log(Level.INFO, "Find by user id execute: {}", userId);
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        try {
            return orderDao.findByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Find by user id error", e);
        }
    }

    @Override
    public List<Order> findByBookId(String bookIdString) throws ServiceException {
        logger.log(Level.INFO, "Find by book id execute: {}", bookIdString);
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        try {
            long bookId = Long.parseLong(bookIdString);
            return orderDao.findByBookId(bookId);
        } catch (DaoException e) {
            throw new ServiceException("Find by book id error", e);
        }
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        logger.log(Level.INFO, "Find all orders execute");
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Find all error", e);
        }
    }

    @Override
    public boolean isCancel(String orderIdString) throws ServiceException {
        logger.log(Level.INFO, "Cancel order execute");
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        long orderId = Long.parseLong(orderIdString);
        try {
            return orderDao.isCancel(orderId);
        } catch (DaoException e) {
            throw new ServiceException("Cancel order error", e);
        }
    }

    @Override
    public boolean isReturn(String orderIdString) throws ServiceException {
        logger.log(Level.INFO, "Return order execute");
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        long orderId = Long.parseLong(orderIdString);
        try {
            return orderDao.isReturn(orderId);
        } catch (DaoException e) {
            throw new ServiceException("Return order error", e);
        }
    }
}