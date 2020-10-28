package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Order;

import java.util.List;


/**
 * The interface Order service.
 */
public interface OrderService {

    List<Order> findPartByUserId(Object userId, int shownRecords, int recordsPerPage) throws ServiceException;

    List<Order> findPartByBookId(String bookId, int shownRecords, int recordsPerPage) throws ServiceException;

    List<Order> findPartOfNew(int shownRecords, int recordsPerPage) throws ServiceException;

    List<Order> findPartOfAll(int shownRecords, int recordsPerPage) throws ServiceException;

    /**
     * Create order.
     *
     * @param userId    the user id
     * @param bookId    the book id
     * @param typeOrder the type order
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isCreateOrder(long userId, String bookId, String typeOrder) throws ServiceException;

    /**
     * Cancel order.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isCancel(String orderId) throws ServiceException;

    /**
     * Return order.
     *
     * @param orderId the order id
     * @param bookId  the book id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isReturn(String orderId, String bookId) throws ServiceException;

    /**
     * Deny order.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isDeny(String orderId) throws ServiceException;

    /**
     * Approve order.
     *
     * @param orderId the order id
     * @param bookId  the book id
     * @param userId  the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isApprove(String orderId, String bookId, String userId) throws ServiceException;

    int findCountUserId(Object userId) throws ServiceException;

    int findCountBookId(String bookId) throws ServiceException;

    int findCountNew() throws ServiceException;

    int findCountAll() throws ServiceException;
}
