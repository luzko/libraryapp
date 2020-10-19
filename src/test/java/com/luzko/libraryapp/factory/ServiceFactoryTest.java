package com.luzko.libraryapp.factory;

import com.luzko.libraryapp.service.impl.AuthorServiceImpl;
import com.luzko.libraryapp.service.impl.BookServiceImpl;
import com.luzko.libraryapp.service.impl.OrderServiceImpl;
import com.luzko.libraryapp.service.impl.UserServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ServiceFactoryTest {
    private ServiceFactory serviceFactory;

    @BeforeClass
    public void setUp() {
        serviceFactory = ServiceFactory.getInstance();
    }

    @Test
    public void getUserServicePositiveTest() {
        Object actual = serviceFactory.getUserService();
        assertTrue(actual instanceof UserServiceImpl);
    }

    @Test
    public void getUserServiceNegativeTest() {
        Object actual = serviceFactory.getUserService();
        assertFalse(actual instanceof BookServiceImpl);
    }

    @Test
    public void getBookServicePositiveTest() {
        Object actual = serviceFactory.getBookService();
        assertTrue(actual instanceof BookServiceImpl);
    }

    @Test
    public void getBookServiceNegativeTest() {
        Object actual = serviceFactory.getBookService();
        assertFalse(actual instanceof UserServiceImpl);
    }

    @Test
    public void getAuthorServicePositiveTest() {
        Object actual = serviceFactory.getAuthorService();
        assertTrue(actual instanceof AuthorServiceImpl);
    }

    @Test
    public void getAuthorServiceNegativeTest() {
        Object actual = serviceFactory.getAuthorService();
        assertFalse(actual instanceof UserServiceImpl);
    }

    @Test
    public void getOrderServicePositiveTest() {
        Object actual = serviceFactory.getOrderService();
        assertTrue(actual instanceof OrderServiceImpl);
    }

    @Test
    public void getOrderServiceNegativeTest() {
        Object actual = serviceFactory.getOrderService();
        assertFalse(actual instanceof UserServiceImpl);
    }
}
