package com.luzko.libraryapp.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    boolean save(T baseEntity) throws ServiceException;

    boolean update(T baseEntity) throws ServiceException;

    boolean delete(long id) throws ServiceException;

    T findById(long id) throws ServiceException;

    List<T> findByName(String name) throws ServiceException;

    List<T> findAll() throws ServiceException;
}
