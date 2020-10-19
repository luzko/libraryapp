package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.BaseEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * The interface Base dao.
 *
 * @param <T> the type parameter
 */
public interface BaseDao<T extends BaseEntity> {
    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<T> findById(long id) throws DaoException;

    /**
     * Find all.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<T> findAll() throws DaoException;

    /**
     * Connection set auto commit.
     *
     * @param connection the connection
     * @param value      the value
     * @throws DaoException the dao exception
     */
    default void connectionSetAutoCommit(Connection connection, boolean value) throws DaoException {
        if (connection != null) {
            try {
                connection.setAutoCommit(value);
            } catch (SQLException e) {
                throw new DaoException("Connection couldn't set autocommit", e);
            }
        }
    }

    /**
     * Connections rollback.
     *
     * @param connection the connection
     * @throws DaoException the dao exception
     */
    default void connectionsRollback(Connection connection) throws DaoException {
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
     * @param connection the connection
     * @throws DaoException the dao exception
     */
    default void connectionCommitChanges(Connection connection) throws DaoException {
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
     * @param connection the connection
     * @throws DaoException the dao exception
     */
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
