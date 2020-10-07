package com.luzko.libraryapp.factory;

import com.luzko.libraryapp.model.dao.book.BookDao;
import com.luzko.libraryapp.model.dao.book.impl.BookDaoImpl;
import com.luzko.libraryapp.model.dao.user.UserDao;
import com.luzko.libraryapp.model.dao.user.impl.UserDaoImpl;

public class DaoFactory {
    private final UserDao userDao;
    private final BookDao bookDao;

    private DaoFactory() {
        userDao = new UserDaoImpl();
        bookDao = new BookDaoImpl();
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
}
