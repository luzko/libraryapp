package com.luzko.libraryapp.factory;

import com.luzko.libraryapp.model.dao.impl.AuthorDaoImpl;
import com.luzko.libraryapp.model.dao.impl.BookDaoImpl;
import com.luzko.libraryapp.model.dao.impl.OrderDaoImpl;
import com.luzko.libraryapp.model.dao.impl.UserDaoImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DaoFactoryTest {
    private DaoFactory daoFactory;

    @BeforeClass
    public void setUp() {
        daoFactory = DaoFactory.getInstance();
    }

    @Test
    public void getUserDAOPositiveTest() {
        Object actual = daoFactory.getUserDAO();
        assertTrue(actual instanceof UserDaoImpl);
    }

    @Test
    public void getUserDAONegativeTest() {
        Object actual = daoFactory.getUserDAO();
        assertFalse(actual instanceof BookDaoImpl);
    }

    @Test
    public void getBookDaoPositiveTest() {
        Object actual = daoFactory.getBookDao();
        assertTrue(actual instanceof BookDaoImpl);
    }

    @Test
    public void getBookDaoNegativeTest() {
        Object actual = daoFactory.getBookDao();
        assertFalse(actual instanceof UserDaoImpl);
    }

    @Test
    public void getAuthorDaoPositiveTest() {
        Object actual = daoFactory.getAuthorDao();
        assertTrue(actual instanceof AuthorDaoImpl);
    }

    @Test
    public void getAuthorDaoNegativeTest() {
        Object actual = daoFactory.getAuthorDao();
        assertFalse(actual instanceof UserDaoImpl);
    }

    @Test
    public void getOrderDaoPositiveTest() {
        Object actual = daoFactory.getOrderDao();
        assertTrue(actual instanceof OrderDaoImpl);
    }

    @Test
    public void getOrderDaoNegativeTest() {
        Object actual = daoFactory.getOrderDao();
        assertFalse(actual instanceof UserDaoImpl);
    }
}
