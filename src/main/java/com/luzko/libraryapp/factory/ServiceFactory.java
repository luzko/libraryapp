package com.luzko.libraryapp.factory;

import com.luzko.libraryapp.service.book.AuthorService;
import com.luzko.libraryapp.service.book.impl.AuthorServiceImpl;
import com.luzko.libraryapp.service.book.BookService;
import com.luzko.libraryapp.service.book.impl.BookServiceImpl;
import com.luzko.libraryapp.service.user.UserService;
import com.luzko.libraryapp.service.user.impl.UserServiceImpl;

public class ServiceFactory {
    private final UserService userService;
    private final BookService bookService;
    private final AuthorService authorService;

    private ServiceFactory() {
        this.userService = new UserServiceImpl();
        this.bookService = new BookServiceImpl();
        this.authorService = new AuthorServiceImpl();
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
}
