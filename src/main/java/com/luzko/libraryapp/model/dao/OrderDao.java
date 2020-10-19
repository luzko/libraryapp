package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.model.entity.OrderType;

import java.util.List;

/**
 * The interface Order dao.
 */
public interface OrderDao extends BaseDao<Order> {
    /**
     * Find by user id.
     *
     * @param userId the user id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findByUserId(long userId) throws DaoException;

    /**
     * Find by book id.
     *
     * @param bookId the book id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findByBookId(long bookId) throws DaoException;

    /**
     * Find new.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findNew() throws DaoException;

    /**
     * Create order.
     *
     * @param userId    the user id
     * @param bookId    the book id
     * @param orderType the order type
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isCreateOrder(long userId, long bookId, OrderType orderType) throws DaoException;

    /**
     * Cancel order.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isCancel(long orderId) throws DaoException;

    /**
     * Return order.
     *
     * @param orderId the order id
     * @param bookId  the book id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isReturn(long orderId, long bookId) throws DaoException;

    /**
     * Deny order.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isDeny(long orderId) throws DaoException;

    /**
     * Approve order.
     *
     * @param orderId the order id
     * @param bookId  the book id
     * @param userId  the user id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isApprove(long orderId, long bookId, long userId) throws DaoException;
}
