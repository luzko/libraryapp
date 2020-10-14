package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.Order;

import java.util.List;

public interface OrderDao extends BaseDao<Order> {

    List<Order> findByUserId(long userId) throws DaoException;

    boolean isCancel(long orderId) throws DaoException;

    boolean isReturn(long orderId) throws DaoException;
}
