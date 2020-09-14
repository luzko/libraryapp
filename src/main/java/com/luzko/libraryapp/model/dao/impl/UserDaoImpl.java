package com.luzko.libraryapp.model.dao.impl;

import com.luzko.libraryapp.connection.ConnectionPool;
import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.StatementSql;
import com.luzko.libraryapp.model.dao.UserDao;
import com.luzko.libraryapp.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean save(User user) throws DaoException {
        //TODO
        boolean isUserAdded = false;
        if (user == null) {
            throw new DaoException("User is not exist");
        }

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.INSERT_USER)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            int rows = statement.executeUpdate();
            isUserAdded = rows != 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DaoException("dao", e); //TODO
        }
        return isUserAdded;
    }

    @Override
    public boolean update(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        return false;
    }

    @Override
    public User findById(long id) throws DaoException {
        return null;
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        return null;
    }

    @Override
    public User findByLogin(String login) throws DaoException {
        ResultSet resultSet = null;
        User user = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = fillUserField(resultSet);
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DaoException("dao", e); //TODO
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public String findPasswordByLogin(String login) throws DaoException {
        ResultSet resultSet = null;
        String password = null;
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_PASS_BY_LOGIN)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            System.out.println("resultSet " + resultSet);
            if (resultSet.next()) {
                System.out.println(true);
                password = resultSet.getString(ColumnName.PASSWORD);
            }
            System.out.println(password);
            return password;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DaoException("dao", e); //TODO
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public void registerUser(User user) throws DaoException {

    }

    @Override
    public List<User> findAll() throws DaoException {
        return null;
    }

    public User fillUserField(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong(ColumnName.USER_ID);
        String login = resultSet.getString(ColumnName.LOGIN);
        String password = resultSet.getString(ColumnName.PASSWORD);
        return new User(userId, login, password);
    }
}
