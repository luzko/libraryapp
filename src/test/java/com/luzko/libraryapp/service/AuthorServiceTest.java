package com.luzko.libraryapp.service;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.model.dao.impl.AuthorDaoImpl;
import com.luzko.libraryapp.model.entity.Author;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

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
        String name = "Alexandr Pushkin";
        try {
            when(daoMock.isNameUnique(any(String.class))).thenReturn(true);
            when(daoMock.add(any(String.class))).thenReturn(true);
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
            when(daoMock.add(any(String.class))).thenReturn(true);
            boolean actual = authorService.add(name);
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findByIdPositiveTest() {
        int authorId = 1;
        Author author = new Author();
        author.setAuthorId(authorId);
        Optional<Author> expectedAuthorOptional = Optional.of(author);
        try {
            when(daoMock.findById(1)).thenReturn(Optional.of(author));
            Optional<Author> actualAuthorOptional = authorService.findById(authorId);
            assertEquals(actualAuthorOptional, expectedAuthorOptional);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findByIdNegativeTest() {
        int authorId = 1;
        Author author = new Author();
        author.setAuthorId(authorId);
        Optional<Author> expectedAuthorOptional = Optional.of(author);
        try {
            when(daoMock.findById(1)).thenReturn(Optional.empty());
            Optional<Author> actualAuthorOptional = authorService.findById(authorId);
            assertNotEquals(actualAuthorOptional, expectedAuthorOptional);
        } catch (DaoException | ServiceException e) {
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

    @AfterClass
    public void tierDown() {
        daoMock = null;
        authorService = null;
    }
}
