package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.service.impl.AuthorServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The interface represents Base DAO.
 */
public interface BaseDao {

    /**
     * The constant logger.
     */
    Logger logger = LogManager.getLogger(AuthorServiceImpl.class);

    /**
     * Connection set auto commit.
     *
     * @param connection the connection instance
     * @param value      the auto commit value
     * @throws DaoException the dao exception
     */
    default void setAutoCommit(Connection connection, boolean value) throws DaoException {
        if (connection != null) {
            try {
                connection.setAutoCommit(value);
            } catch (SQLException e) {
                throw new DaoException("Connection couldn't set autocommit", e);
            }
        }
    }

    /**
     * Connection rollback.
     *
     * @param connection the connection instance
     * @throws DaoException the dao exception
     */
    default void rollback(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DaoException("Connection couldn't rollback result", e);
            }
        }
    }

    /**
     * Connection commit changes.
     *
     * @param connection the connection instance
     * @throws DaoException the dao exception
     */
    default void commit(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new DaoException("Connection couldn't commit changes", e);
            }
        }
    }

    /**
     * Close connection.
     *
     * @param connection the connection instance
     * @throws DaoException the dao exception
     */
    default void close(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection couldn't be closed");
            }
        }
    }
}
