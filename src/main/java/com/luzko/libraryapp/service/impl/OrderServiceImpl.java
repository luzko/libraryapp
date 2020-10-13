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
            throw new ServiceException("Find by user id", e);
        }
    }
}