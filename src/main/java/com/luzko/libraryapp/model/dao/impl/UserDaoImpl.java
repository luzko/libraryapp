package com.luzko.libraryapp.model.dao.impl;

import com.luzko.libraryapp.connection.ConnectionPool;
import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.dao.StatementSql;
import com.luzko.libraryapp.model.dao.UserDao;
import com.luzko.libraryapp.model.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl INSTANCE = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean save(User user) throws DaoException {
        boolean isUserAdded = false;
        if (user == null) {
            throw new DaoException("User is not exist");
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
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
    public List<User> findByName(String name) throws DaoException {
        return null;
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        return null;
    }

    @Override
    public List<User> findAll() throws DaoException {
        return null;
    }
}
