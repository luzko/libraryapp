package com.luzko.libraryapp.model.dao.book.impl;

import com.luzko.libraryapp.model.builder.BookBuilder;
import com.luzko.libraryapp.connection.ConnectionPool;
import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.StatementSql;
import com.luzko.libraryapp.model.dao.book.BookDao;
import com.luzko.libraryapp.model.entity.book.Book;
import com.luzko.libraryapp.model.entity.book.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {

    @Override
    public Optional<Book> findById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_BOOK_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return createBookFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find by id error", e);
        }
    }

    @Override
    public List<Book> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_ALL_BOOKS)) {
            ResultSet resultSet = statement.executeQuery();
            return createBooksFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error in Find all", e);
        }
    }

    private List<Book> createBooksFromResultSet(ResultSet resultSet) throws SQLException {
        List<Book> bookList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                Optional<Book> bookOptional = createBookOverview(resultSet);
                bookOptional.ifPresent(bookList::add);
            }
        }
        return bookList;
    }

    private Optional<Book> createBookFromResultSet(ResultSet resultSet) throws SQLException {
        return resultSet != null && resultSet.next() ? createBook(resultSet) : Optional.empty();
    }

    private Optional<Book> createBook(ResultSet resultSet) throws SQLException {
        BookBuilder bookBuilder = new BookBuilder()
                .setBookId(resultSet.getLong(ColumnName.BOOK_ID))
                .setTitle(resultSet.getString(ColumnName.TITLE))
                .setYear(resultSet.getInt(ColumnName.YEAR))
                .setPage(resultSet.getInt(ColumnName.PAGES))
                .setDescription(resultSet.getString(ColumnName.DESCRIPTION))
                .setNumberCopy(resultSet.getInt(ColumnName.NUMBER_COPIES))
                .setCategory(Category.defineRoleById(resultSet.getInt(ColumnName.CATEGORY_ID_FK)))
                .setAuthor(resultSet.getString(ColumnName.AUTHORS));
        Book book = new Book(bookBuilder);
        Optional<Book> bookOptional = Optional.empty();
        if (book.getCategory() != null) {
            bookOptional = Optional.of(book);
        }
        return bookOptional;
    }

    private Optional<Book> createBookOverview(ResultSet resultSet) throws SQLException {
        BookBuilder bookBuilder = new BookBuilder()
                .setBookId(resultSet.getLong(ColumnName.BOOK_ID))
                .setTitle(resultSet.getString(ColumnName.TITLE))
                .setCategory(Category.defineRoleById(resultSet.getInt(ColumnName.CATEGORY_ID_FK)))
                .setAuthor(resultSet.getString(ColumnName.AUTHORS));
        Book book = new Book(bookBuilder);
        Optional<Book> bookOptional = Optional.empty();
        if (book.getCategory() != null) {
            bookOptional = Optional.of(book);
        }
        return bookOptional;
    }
}
