package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.model.entity.OrderType;

import java.util.List;

/**
 * The interface Order dao.
 */
public interface OrderDao extends BaseDao {

    List<Order> findPartByUserId(long userId, int shownRecords, int recordsPerPage) throws DaoException;

    List<Order> findPartByBookId(long bookId, int shownRecords, int recordsPerPage) throws DaoException;

    List<Order> findPartOfNew(int shownRecords, int recordsPerPage) throws DaoException;

    List<Order> findPartOfAll(int shownRecords, int recordsPerPage) throws DaoException;

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

    int findCountByUserId(long userId) throws DaoException;

    int findCountByBookId(long bookId) throws DaoException;

    int findCountNew() throws DaoException;

    int findCountAll() throws DaoException;
}
