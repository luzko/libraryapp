package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Order;

import java.util.List;


/**
 * The interface represents User Service.
 */
public interface OrderService {
    /**
     * Find count orders.
     *
     * @param typeOrder the order type
     * @return count orders
     * @throws ServiceException the service exception
     */
    int findCount(String typeOrder) throws ServiceException;

    /**
     * Find count orders.
     *
     * @param paramId   the param id
     * @param typeOrder the order type
     * @return count orders
     * @throws ServiceException the service exception
     */
    int findCount(long paramId, String typeOrder) throws ServiceException;

    /**
     * Find part order list.
     *
     * @param typeOrder      the order type
     * @param shownRecords   the shown records
     * @param recordsPerPage the records per page
     * @return the list of found orders
     * @throws ServiceException the service exception
     */
    List<Order> findPart(String typeOrder, int shownRecords, int recordsPerPage) throws ServiceException;

    /**
     * Find part order list.
     *
     * @param paramId        the param id
     * @param typeOrder      the order type
     * @param shownRecords   the shown records
     * @param recordsPerPage the records per page
     * @return the list of found orders
     * @throws ServiceException the service exception
     */
    List<Order> findPart(long paramId, String typeOrder, int shownRecords, int recordsPerPage) throws ServiceException;

    /**
     * Create order.
     *
     * @param userId    the user id
     * @param bookId    the book id
     * @param typeOrder the order type
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
}
