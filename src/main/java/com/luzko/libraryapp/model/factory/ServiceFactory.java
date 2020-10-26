package com.luzko.libraryapp.model.factory;

import com.luzko.libraryapp.model.service.AuthorService;
import com.luzko.libraryapp.model.service.impl.AuthorServiceImpl;
import com.luzko.libraryapp.model.service.BookService;
import com.luzko.libraryapp.model.service.impl.BookServiceImpl;
import com.luzko.libraryapp.model.service.OrderService;
import com.luzko.libraryapp.model.service.impl.OrderServiceImpl;
import com.luzko.libraryapp.model.service.UserService;
import com.luzko.libraryapp.model.service.impl.UserServiceImpl;

/**
 * The type Service factory.
 */
public class ServiceFactory {
    private final UserService userService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final OrderService orderService;

    private ServiceFactory() {
        this.userService = new UserServiceImpl();
        this.bookService = new BookServiceImpl();
        this.authorService = new AuthorServiceImpl();
        this.orderService = new OrderServiceImpl();
    }

    private static class ServiceFactorySingletonHolder {
        /**
         * The Instance.
         */
        static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceFactory getInstance() {
        return ServiceFactory.ServiceFactorySingletonHolder.INSTANCE;
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets book service.
     *
     * @return the book service
     */
    public BookService getBookService() {
        return bookService;
    }

    /**
     * Gets author service.
     *
     * @return the author service
     */
    public AuthorService getAuthorService() {
        return authorService;
    }

    /**
     * Gets order service.
     *
     * @return the order service
     */
    public OrderService getOrderService() {
        return orderService;
    }
}
