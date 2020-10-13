package com.luzko.libraryapp.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Order;

import java.util.List;


public interface OrderService {

    List<Order> findByUserId(long userId) throws ServiceException;

}
