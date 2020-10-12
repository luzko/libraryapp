package com.luzko.libraryapp.factory;

import com.luzko.libraryapp.service.AuthorService;
import com.luzko.libraryapp.service.impl.AuthorServiceImpl;
import com.luzko.libraryapp.service.BookService;
import com.luzko.libraryapp.service.impl.BookServiceImpl;
import com.luzko.libraryapp.service.OrderService;
import com.luzko.libraryapp.service.impl.OrderServiceImpl;
import com.luzko.libraryapp.service.UserService;
import com.luzko.libraryapp.service.impl.UserServiceImpl;

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
        static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return ServiceFactory.ServiceFactorySingletonHolder.INSTANCE;
    }

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public AuthorService getAuthorService() {
        return authorService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
