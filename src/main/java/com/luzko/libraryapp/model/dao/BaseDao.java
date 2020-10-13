package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.BaseEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends BaseEntity> {

    Optional<T> findById(long id) throws DaoException;

    List<T> findAll() throws DaoException;

    default void connectionSetAutoCommit(Connection connection, boolean value) throws DaoException {
        if (connection != null) {
            try {
                connection.setAutoCommit(value);
            } catch (SQLException e) {
                throw new DaoException("Connection couldn't set autocommit", e);
            }
        }
    }

    default void connectionsRollback(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DaoException("Connection couldn't rollback result", e);
            }
        }
    }

    default void connectionCommitChanges(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new DaoException("Connection couldn't commit changes", e);
            }
        }
    }

    default void closeConnection(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException("Connection couldn't be closed", e);
            }
        }
    }
}
