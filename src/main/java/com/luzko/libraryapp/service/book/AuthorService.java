package com.luzko.libraryapp.service.book;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.book.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(long id) throws ServiceException;

    List<Author> findAll() throws ServiceException;

    boolean add(String name) throws ServiceException;
}
