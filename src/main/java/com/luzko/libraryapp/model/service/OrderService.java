package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Order;

import java.util.List;


/**
 * The interface Order service.
 */
public interface OrderService {
    /**
     * Find by user id.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findPartByUserId(Object userId, int shownRecords, int recordsPerPage) throws ServiceException;

    /**
     * Find by book id.
     *
     * @param bookId the book id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findByBookId(String bookId) throws ServiceException;

    /**
     * Find all.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAll() throws ServiceException;

    /**
     * Find new.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findNew() throws ServiceException;

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

    int findCountByUserId(Object userId) throws ServiceException;
}
