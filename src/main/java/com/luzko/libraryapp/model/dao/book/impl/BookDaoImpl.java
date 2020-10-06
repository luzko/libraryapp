package com.luzko.libraryapp.model.dao.book.impl;

import com.luzko.libraryapp.connection.ConnectionPool;
import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.dao.StatementSql;
import com.luzko.libraryapp.model.dao.book.BookDao;
import com.luzko.libraryapp.model.entity.book.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private static final BookDaoImpl instance = new BookDaoImpl();
    //TODO add logger..

    private BookDaoImpl() {

    }

    public static BookDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(Book book) throws DaoException {
        return false;
    }

    @Override
    public boolean remove(long id) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Book book) throws DaoException {
        return false;
    }

    @Override
    public Book findById(long id) throws DaoException {
        return null;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_ALL_BOOKS)) {
            ResultSet resultSet = statement.executeQuery();
            //return createBooksFromResultSet(resultSet);
            return null;
        } catch (SQLException e) {
            throw new DaoException("dao", e); //TODO
        }
    }
}
