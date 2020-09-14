package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.BaseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T extends BaseEntity> {

    //Logger logger = LogManager.getLogger(BaseDao.class);

    boolean save(T baseEntity) throws DaoException;

    boolean update(T baseEntity) throws DaoException;

    boolean delete(long id) throws DaoException;

    T findById(long id) throws DaoException;

    List<T> findAll() throws DaoException;

    default void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            //logger.log(Level.ERROR, "Error while closing resultSet");
        }
    }
}
