package com.luzko.libraryapp.model.dao.impl;

import com.luzko.libraryapp.model.builder.BookBuilder;
import com.luzko.libraryapp.model.connection.ConnectionPool;
import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.StatementSql;
import com.luzko.libraryapp.model.dao.BookDao;
import com.luzko.libraryapp.model.entity.Book;
import com.luzko.libraryapp.model.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Book dao.
 */
public class BookDaoImpl implements BookDao {
    private static final BookDao INSTANCE = new BookDaoImpl();

    private BookDaoImpl() {

    }

    public static BookDao getInstance() {
        return INSTANCE;
    }

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

    @Override
    public boolean isParameterUnique(String title, int year, int pages) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_COUNT_BY_PARAMETER)) {
            statement.setString(1, title);
            statement.setInt(2, year);
            statement.setInt(3, pages);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(ColumnName.BOOK_COUNT);
            return count == 0;
        } catch (SQLException e) {
            throw new DaoException("Parameter unique error", e);
        }
    }

    @Override
    public boolean add(Book book, long authorId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean isBookAdd = false;
        try (PreparedStatement statementBook = connection.prepareStatement(StatementSql.ADD_BOOK, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementBookAuthors = connection.prepareStatement(StatementSql.ADD_BOOK_AUTHORS)) {
            connectionSetAutoCommit(connection, false);
            if (countBook(statementBook, book) == 1) {
                ResultSet generatedKey = statementBook.getGeneratedKeys();
                generatedKey.next();
                long bookId = generatedKey.getLong(1);
                statementBookAuthors.setLong(1, bookId);
                statementBookAuthors.setLong(2, authorId);
                isBookAdd = statementBookAuthors.executeUpdate() > 0;
            }
            connectionCommitChanges(connection);
            return isBookAdd;
        } catch (SQLException e) {
            connectionsRollback(connection);
            throw new DaoException("Book add error", e);
        } finally {
            connectionSetAutoCommit(connection, true);
            closeConnection(connection);
        }
    }

    private int countBook(PreparedStatement statementBook, Book book) throws SQLException {
        statementBook.setString(1, book.getTitle());
        statementBook.setInt(2, book.getYear());
        statementBook.setInt(3, book.getPage());
        statementBook.setString(4, book.getDescription());
        statementBook.setInt(5, book.getNumberCopy());
        statementBook.setInt(6, book.getCategory().defineId());
        return statementBook.executeUpdate();
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
        Optional<Book> bookOptional = Optional.empty();
        Optional<Category> categoryOptional = Category.defineCategoryById(resultSet.getInt(ColumnName.CATEGORY_ID_FK));
        if (categoryOptional.isPresent()) {
            BookBuilder bookBuilder = new BookBuilder()
                    .setBookId(resultSet.getLong(ColumnName.BOOK_ID))
                    .setTitle(resultSet.getString(ColumnName.TITLE))
                    .setYear(resultSet.getInt(ColumnName.YEAR))
                    .setPage(resultSet.getInt(ColumnName.PAGES))
                    .setDescription(resultSet.getString(ColumnName.DESCRIPTION))
                    .setNumberCopy(resultSet.getInt(ColumnName.NUMBER))
                    .setCategory(categoryOptional.get())
                    .setAuthor(resultSet.getString(ColumnName.AUTHORS));
            Book book = new Book(bookBuilder);
            bookOptional = Optional.of(book);
        }
        return bookOptional;
    }

    private Optional<Book> createBookOverview(ResultSet resultSet) throws SQLException {
        Optional<Book> bookOptional = Optional.empty();
        Optional<Category> categoryOptional = Category.defineCategoryById(resultSet.getInt(ColumnName.CATEGORY_ID_FK));
        if (categoryOptional.isPresent()) {
            BookBuilder bookBuilder = new BookBuilder()
                    .setBookId(resultSet.getLong(ColumnName.BOOK_ID))
                    .setTitle(resultSet.getString(ColumnName.TITLE))
                    .setCategory(categoryOptional.get())
                    .setAuthor(resultSet.getString(ColumnName.AUTHORS));
            Book book = new Book(bookBuilder);
            bookOptional = Optional.of(book);
        }
        return bookOptional;
    }
}
