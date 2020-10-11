package com.luzko.libraryapp.factory;

import com.luzko.libraryapp.model.dao.book.AuthorDao;
import com.luzko.libraryapp.model.dao.book.impl.AuthorDaoImpl;
import com.luzko.libraryapp.model.dao.book.BookDao;
import com.luzko.libraryapp.model.dao.book.impl.BookDaoImpl;
import com.luzko.libraryapp.model.dao.order.OrderDao;
import com.luzko.libraryapp.model.dao.order.impl.OrderDaoImpl;
import com.luzko.libraryapp.model.dao.user.UserDao;
import com.luzko.libraryapp.model.dao.user.impl.UserDaoImpl;

public class DaoFactory {
    private final UserDao userDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final OrderDao orderDao;

    private DaoFactory() {
        this.userDao = new UserDaoImpl();
        this.bookDao = new BookDaoImpl();
        this.authorDao = new AuthorDaoImpl();
        this.orderDao = new OrderDaoImpl();
    }

    private static class DAOFactorySingletonHolder {
        static final DaoFactory INSTANCE = new DaoFactory();
    }

    public static DaoFactory getInstance() {
        return DAOFactorySingletonHolder.INSTANCE;
    }

    public UserDao getUserDAO() {
        return userDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public AuthorDao getAuthorDao() {
        return authorDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }
}
