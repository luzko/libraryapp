package com.luzko.libraryapp.model.dao.author.impl;

import com.luzko.libraryapp.connection.ConnectionPool;
import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.StatementSql;
import com.luzko.libraryapp.model.dao.author.AuthorDao;
import com.luzko.libraryapp.model.entity.author.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorDaoImpl implements AuthorDao {

    @Override
    public Optional<Author> findById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_AUTHOR_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return createAuthorFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find by id error", e);
        }
    }

    @Override
    public List<Author> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_ALL_AUTHORS)) {
            ResultSet resultSet = statement.executeQuery();
            return createBooksFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error in Find all", e);
        }
    }

    @Override
    public boolean add(String name) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.ADD_AUTHOR)) {
            statement.setString(1, name);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Add error", e);
        }
    }

    @Override
    public boolean isNameUnique(String name) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FINE_COUNT_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(ColumnName.AUTHOR_COUNT);
            return count == 0;
        } catch (SQLException e) {
            throw new DaoException("Name unique error", e);
        }
    }

    private List<Author> createBooksFromResultSet(ResultSet resultSet) throws SQLException {
        List<Author> authorList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                Optional<Author> authorOptional = createAuthor(resultSet);
                authorOptional.ifPresent(authorList::add);
            }
        }
        return authorList;
    }

    private Optional<Author> createAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        return resultSet != null && resultSet.next() ? createAuthor(resultSet) : Optional.empty();
    }

    private Optional<Author> createAuthor(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setAuthorId(resultSet.getLong(ColumnName.AUTHOR_ID));
        author.setName(resultSet.getString(ColumnName.AUTHOR));
        return Optional.of(author);
    }
}
