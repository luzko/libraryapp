package com.luzko.libraryapp.service.book;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.book.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll() throws ServiceException;
}
