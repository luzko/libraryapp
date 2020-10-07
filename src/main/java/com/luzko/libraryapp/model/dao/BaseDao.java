package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends BaseEntity> {

    boolean add(T baseEntity) throws DaoException;

    Optional<T> findById(long id) throws DaoException;

    List<T> findAll() throws DaoException;
}
