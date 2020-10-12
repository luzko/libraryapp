package com.luzko.libraryapp.model.dao.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.dao.OrderDao;
import com.luzko.libraryapp.model.entity.Order;

import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Optional<Order> findById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() throws DaoException {
        return null;
    }
}
