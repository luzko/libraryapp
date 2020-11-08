package com.luzko.libraryapp.model.dao.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.builder.BookBuilder;
import com.luzko.libraryapp.model.connection.ConnectionPool;
import com.luzko.libraryapp.model.dao.BookDao;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.StatementSql;
import com.luzko.libraryapp.model.entity.Book;
import com.luzko.libraryapp.model.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type represents Book DAO implementation.
 */
public class BookDaoImpl implements BookDao {
    private static final BookDao INSTANCE = new BookDaoImpl();
    private static final String PERCENT = "%";

    private BookDaoImpl() {

    }

    public static BookDao getInstance() {
        return INSTANCE;
    }

    @Override
    public int findCount() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_COUNT_BOOK)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(ColumnName.COUNT);
        } catch (SQLException e) {
            throw new DaoException("Find count all records", e);
        }
    }

    @Override
    public int findCount(String searchName) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_COUNT_SEARCH_BOOK)) {
            statement.setString(1, PERCENT + searchName + PERCENT);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(ColumnName.COUNT);
        } catch (SQLException e) {
            throw new DaoException("Find count records", e);
        }
    }

    @Override
    public List<Book> findPartOfAll(int recordsShown, int recordsPerPage) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_PART_BOOKS)) {
            statement.setInt(1, recordsPerPage);
            statement.setInt(2, recordsShown);
            ResultSet resultSet = statement.executeQuery();
            return createBooksFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find all error", e);
        }
    }

    @Override
    public List<Book> findByName(String searchName, int recordsShown, int recordsPerPage) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_BY_NAME)) {
            statement.setString(1, PERCENT + searchName + PERCENT);
            statement.setInt(2, recordsPerPage);
            statement.setInt(3, recordsShown);
            ResultSet resultSet = statement.executeQuery();
            return createBooksFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find all error", e);
        }
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
            setAutoCommit(connection, false);
            if (addBook(statementBook, book) == 1) {
                ResultSet generatedKey = statementBook.getGeneratedKeys();
                generatedKey.next();
                long bookId = generatedKey.getLong(1);
                statementBookAuthors.setLong(1, bookId);
                statementBookAuthors.setLong(2, authorId);
                isBookAdd = statementBookAuthors.executeUpdate() > 0;
            }
            commit(connection);
            return isBookAdd;
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException("Book add error", e);
        } finally {
            setAutoCommit(connection, true);
            close(connection);
        }
    }

    @Override
    public boolean remove(long bookId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean isBookRemove = false;
        try (PreparedStatement statementBook = connection.prepareStatement(StatementSql.REMOVE_BOOK);
             PreparedStatement statementOrder = connection.prepareStatement(StatementSql.CANCEL_ORDER_BY_BOOK)) {
            setAutoCommit(connection, false);
            if (removeBook(statementBook, bookId) == 1) {
                statementOrder.setLong(1, bookId);
                statementOrder.executeUpdate();
                isBookRemove = true;
            }
            commit(connection);
            return isBookRemove;
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException("Book remove error", e);
        } finally {
            setAutoCommit(connection, true);
            close(connection);
        }
    }

    private int addBook(PreparedStatement statement, Book book) throws SQLException {
        statement.setString(1, book.getTitle());
        statement.setInt(2, book.getYear());
        statement.setInt(3, book.getPage());
        statement.setString(4, book.getDescription());
        statement.setInt(5, book.getNumberCopy());
        statement.setInt(6, book.getCategory().defineId());
        return statement.executeUpdate();
    }

    private int removeBook(PreparedStatement statement, long bookId) throws SQLException {
        statement.setLong(1, bookId);
        return statement.executeUpdate();
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
