package com.luzko.libraryapp.service.book;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.book.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(String bookId) throws ServiceException;

    List<Book> findAll() throws ServiceException;

    boolean isParameterUnique(String title, String year, String pages) throws ServiceException;

    boolean add(Map<String, String> bookParameter) throws ServiceException;
}
