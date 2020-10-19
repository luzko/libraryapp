package com.luzko.libraryapp.factory;

import com.luzko.libraryapp.model.dao.AuthorDao;
import com.luzko.libraryapp.model.dao.impl.AuthorDaoImpl;
import com.luzko.libraryapp.model.dao.BookDao;
import com.luzko.libraryapp.model.dao.impl.BookDaoImpl;
import com.luzko.libraryapp.model.dao.OrderDao;
import com.luzko.libraryapp.model.dao.impl.OrderDaoImpl;
import com.luzko.libraryapp.model.dao.UserDao;
import com.luzko.libraryapp.model.dao.impl.UserDaoImpl;

/**
 * The type Dao factory.
 */
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
        /**
         * The Instance.
         */
        static final DaoFactory INSTANCE = new DaoFactory();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DaoFactory getInstance() {
        return DAOFactorySingletonHolder.INSTANCE;
    }

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    public UserDao getUserDAO() {
        return userDao;
    }

    /**
     * Gets book dao.
     *
     * @return the book dao
     */
    public BookDao getBookDao() {
        return bookDao;
    }

    /**
     * Gets author dao.
     *
     * @return the author dao
     */
    public AuthorDao getAuthorDao() {
        return authorDao;
    }

    /**
     * Gets order dao.
     *
     * @return the order dao
     */
    public OrderDao getOrderDao() {
        return orderDao;
    }
}
