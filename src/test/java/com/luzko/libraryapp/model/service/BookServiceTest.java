package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.dao.impl.BookDaoImpl;
import com.luzko.libraryapp.model.entity.Book;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

@PrepareForTest(BookDaoImpl.class)
public class BookServiceTest {
    private BookDaoImpl daoMock;
    private BookService bookService;

    @BeforeClass
    public void setUp() {
        bookService = ServiceFactory.getInstance().getBookService();
    }

    @BeforeMethod
    public void setUpMock() {
        daoMock = mock(BookDaoImpl.class);
        Whitebox.setInternalState(BookDaoImpl.class, "INSTANCE", daoMock);
    }

    @Test
    public void findByIdPositiveTest() {
        long bookId = 1;
        Book book = new Book();
        book.setBookId(bookId);
        Optional<Book> expectedBookOptional = Optional.of(book);
        try {
            when(daoMock.findById(bookId)).thenReturn(Optional.of(book));
            Optional<Book> actualBookOptional = bookService.findById(String.valueOf(bookId));
            assertEquals(actualBookOptional, expectedBookOptional);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findByIdNegativeTest() {
        long bookId = 1;
        Book book = new Book();
        book.setBookId(bookId);
        Optional<Book> expectedBookOptional = Optional.of(book);
        try {
            when(daoMock.findById(bookId)).thenReturn(Optional.empty());
            Optional<Book> actualBookOptional = bookService.findById(String.valueOf(bookId));
            assertNotEquals(actualBookOptional, expectedBookOptional);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findByIdExceptionTest() {
        long bookId = 1;
        try {
            when(daoMock.findById(bookId)).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> bookService.findById(String.valueOf(bookId)));
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void findAllPositiveTest() {
        Book book = new Book();
        List<Book> expectedBookList = List.of(book);
        try {
            when(daoMock.findAll()).thenReturn(List.of(book));
            List<Book> actualBookList = bookService.findAll();
            assertEquals(actualBookList, expectedBookList);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findAllNegativeTest() {
        Book book = new Book();
        List<Book> expectedBookList = Collections.emptyList();
        try {
            when(daoMock.findAll()).thenReturn(List.of(book));
            List<Book> actualBookList = bookService.findAll();
            assertNotEquals(actualBookList, expectedBookList);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findAllExceptionTest() {
        try {
            when(daoMock.findAll()).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> bookService.findAll());
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void isParameterUniquePositiveTest() {
        String title = "My new book";
        String yearValue = "2000";
        String pagesValue = "111";
        try {
            when(daoMock.isParameterUnique(anyString(), anyInt(), anyInt())).thenReturn(true);
            boolean actual = bookService.isParameterUnique(title, yearValue, pagesValue);
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void isParameterUniqueNegativeTest() {
        String title = "My new book";
        String yearValue = "3000";
        String pagesValue = "111";
        try {
            when(daoMock.isParameterUnique(anyString(), anyInt(), anyInt())).thenReturn(true);
            boolean actual = bookService.isParameterUnique(title, yearValue, pagesValue);
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void isParameterUniqueExceptionTest() {
        String title = "My new book";
        String yearValue = "2000";
        String pagesValue = "111";
        try {
            when(daoMock.isParameterUnique(anyString(), anyInt(), anyInt())).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> bookService.isParameterUnique(title, yearValue, pagesValue));
        } catch (DaoException e) {
            fail();
        }
    }

    @AfterClass
    public void tierDown() {
        daoMock = null;
        bookService = null;
    }
}
