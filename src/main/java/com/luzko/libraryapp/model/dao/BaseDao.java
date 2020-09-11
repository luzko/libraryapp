package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {

    boolean save(T baseEntity) throws DaoException;

    boolean update(T baseEntity) throws DaoException;

    boolean delete(long id) throws DaoException;

    T findById(long id) throws DaoException;

    List<T> findAll() throws DaoException;
}
