package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.dao.impl.AuthorDaoImpl;
import com.luzko.libraryapp.model.entity.Author;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

@PrepareForTest(AuthorDaoImpl.class)
public class AuthorServiceTest {
    private AuthorDaoImpl daoMock;
    private AuthorService authorService;

    /*@BeforeClass
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
    public void findByIdPositiveTest() {
        long authorId = 1;
        Author author = new Author();
        author.setAuthorId(authorId);
        Optional<Author> expectedAuthorOptional = Optional.of(author);
        try {
            when(daoMock.findById(authorId)).thenReturn(Optional.of(author));
            Optional<Author> actualAuthorOptional = authorService.findById(authorId);
            assertEquals(actualAuthorOptional, expectedAuthorOptional);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findByIdNegativeTest() {
        long authorId = 1;
        Author author = new Author();
        author.setAuthorId(authorId);
        Optional<Author> expectedAuthorOptional = Optional.of(author);
        try {
            when(daoMock.findById(authorId)).thenReturn(Optional.empty());
            Optional<Author> actualAuthorOptional = authorService.findById(authorId);
            assertNotEquals(actualAuthorOptional, expectedAuthorOptional);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findByIdExceptionTest() {
        long authorId = 1;
        try {
            when(daoMock.findById(authorId)).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> authorService.findById(authorId));
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
    }*/
}
