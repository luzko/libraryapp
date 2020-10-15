package com.luzko.libraryapp.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Order;

import java.util.List;


public interface OrderService {
    List<Order> findByUserId(long userId) throws ServiceException;

    List<Order> findByBookId(String bookId) throws ServiceException;

    List<Order> findAll() throws ServiceException;

    List<Order> findNew() throws ServiceException;

    boolean isCreateOrder(long userId, String bookId, String typeOrder) throws ServiceException;

    boolean isCancel(String orderId) throws ServiceException;

    boolean isReturn(String orderId, String bookId) throws ServiceException;

    boolean isDeny(String orderId) throws ServiceException;

    boolean isApprove(String orderId, String bookId, String userId) throws ServiceException;
}
