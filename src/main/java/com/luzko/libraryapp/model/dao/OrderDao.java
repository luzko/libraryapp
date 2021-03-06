package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.model.entity.OrderType;

import java.util.List;

/**
 * The interface represents User DAO.
 */
public interface OrderDao extends BaseDao {

    /**
     * Find count orders.
     *
     * @param typeOrder the order type
     * @return count orders
     * @throws DaoException the dao exception
     */
    int findCount(String typeOrder) throws DaoException;

    /**
     * Find count orders.
     *
     * @param paramId   the param id
     * @param typeOrder the order type
     * @return count orders
     * @throws DaoException the dao exception
     */
    int findCount(long paramId, String typeOrder) throws DaoException;

    /**
     * Find part order list.
     *
     * @param typeOrder      the order type
     * @param shownRecords   the shown records
     * @param recordsPerPage the records per page
     * @return the list of found orders
     * @throws DaoException the dao exception
     */
    List<Order> findPart(String typeOrder, int shownRecords, int recordsPerPage) throws DaoException;

    /**
     * Find part order list.
     *
     * @param paramId        the param id
     * @param typeOrder      the order type
     * @param shownRecords   the shown records
     * @param recordsPerPage the records per page
     * @return the list of found orders
     * @throws DaoException the dao exception
     */
    List<Order> findPart(long paramId, String typeOrder, int shownRecords, int recordsPerPage) throws DaoException;

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
