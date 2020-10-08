package com.luzko.libraryapp.service.book;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.book.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(String bookId) throws ServiceException;

    List<Book> findAll() throws ServiceException;
}
