package com.luzko.libraryapp.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Order;

import java.util.List;


public interface OrderService {
    List<Order> findByUserId(long userId) throws ServiceException;

    List<Order> findByBookId(String bookId) throws ServiceException;

    boolean isCancel(String orderId) throws ServiceException;

    boolean isReturn(String orderId) throws ServiceException;
}
