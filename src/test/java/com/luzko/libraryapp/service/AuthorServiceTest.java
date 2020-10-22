package com.luzko.libraryapp.service;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.model.dao.impl.AuthorDaoImpl;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

@PrepareForTest(AuthorDaoImpl.class)
public class AuthorServiceTest {
    private AuthorDaoImpl daoMock;
    private AuthorService authorService;

    @BeforeClass
    public void setUp() {
        daoMock = mock(AuthorDaoImpl.class);
        authorService = ServiceFactory.getInstance().getAuthorService();
        Whitebox.setInternalState(AuthorDaoImpl.class, "INSTANCE", daoMock);
    }

    @Test
    public void addPositiveTest() {
        String inputValue = "Alexandr Pushkin";
        try {
            when(daoMock.isNameUnique(any(String.class))).thenReturn(true);
            when(daoMock.add(any(String.class))).thenReturn(true);
            boolean actual = authorService.add(inputValue);
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void addNegativeTest() {
        String inputValue = null;
        try {
            when(daoMock.isNameUnique(any(String.class))).thenReturn(true);
            when(daoMock.add(any(String.class))).thenReturn(true);
            boolean actual = authorService.add(inputValue);
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @AfterClass
    public void tierDown() {
        daoMock = null;
        authorService = null;
    }
}
