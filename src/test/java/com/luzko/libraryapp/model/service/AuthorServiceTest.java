package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.dao.impl.AuthorDaoImpl;
import com.luzko.libraryapp.model.entity.Author;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

@PrepareForTest(AuthorDaoImpl.class)
public class AuthorServiceTest {
    private AuthorDaoImpl daoMock;
    private AuthorService authorService;

    @BeforeClass
    public void setUp() {
        authorService = ServiceFactory.getInstance().getAuthorService();
    }

    @BeforeMethod
    public void setUpMock() {
        daoMock = mock(AuthorDaoImpl.class);
        Whitebox.setInternalState(AuthorDaoImpl.class, "INSTANCE", daoMock);
    }

    @Test
    public void addPositiveTest() {
        String name = "Alexandr Pushkin";
        try {
            when(daoMock.isNameUnique(anyString())).thenReturn(true);
            when(daoMock.add(anyString())).thenReturn(true);
            boolean actual = authorService.add(name);
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void addNegativeTest() {
        String name = null;
        try {
            when(daoMock.add(anyString())).thenReturn(true);
            boolean actual = authorService.add(name);
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void addExceptionTest() {
        String name = "Alexandr Pushkin";
        try {
            when(daoMock.isNameUnique(anyString())).thenThrow(new DaoException());
            when(daoMock.add(anyString())).thenReturn(true);
            assertThrows(ServiceException.class, () -> authorService.add(name));
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void findAllPositiveTest() {
        Author author = new Author();
        List<Author> expectedAuthorList = List.of(author);
        try {
            when(daoMock.findAll()).thenReturn(List.of(author));
            List<Author> actualAuthorList = authorService.findAll();
            assertEquals(actualAuthorList, expectedAuthorList);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findAllNegativeTest() {
        Author author = new Author();
        List<Author> expectedAuthorList = Collections.emptyList();
        try {
            when(daoMock.findAll()).thenReturn(List.of(author));
            List<Author> actualAuthorList = authorService.findAll();
            assertNotEquals(actualAuthorList, expectedAuthorList);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findAllExceptionTest() {
        try {
            when(daoMock.findAll()).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> authorService.findAll());
        } catch (DaoException e) {
            fail();
        }
    }

    @AfterClass
    public void tierDown() {
        daoMock = null;
        authorService = null;
    }
}
