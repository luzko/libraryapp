package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.model.entity.OrderType;

import java.util.List;

public interface OrderDao extends BaseDao<Order> {

    List<Order> findByUserId(long userId) throws DaoException;

    List<Order> findByBookId(long bookId) throws DaoException;

    List<Order> findNew() throws DaoException;

    boolean isCreateOrder(long userId, long bookId, OrderType orderType) throws DaoException;

    boolean isCancel(long orderId) throws DaoException;

    boolean isReturn(long orderId, long bookId) throws DaoException;
}
