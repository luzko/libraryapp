package com.luzko.libraryapp.model.dao.impl;

import com.luzko.libraryapp.connection.ConnectionPool;
import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.dao.StatementSql;
import com.luzko.libraryapp.model.dao.UserDao;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.model.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.luzko.libraryapp.model.dao.ColumnName.*;

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
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.ADD_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getUserRole().getId());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setString(6, user.getEmail());
            statement.setBoolean(7, user.isEnabled());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("dao", e); //TODO
        }
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
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            return createUserFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("dao", e); //TODO
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        return null;
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException, DaoException {
        if (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getLong(USER_ID));
            user.setLogin(resultSet.getString(LOGIN));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setUserRole(UserRole.getRoleById(resultSet.getInt(ROLE_ID_FK)));
            user.setName(resultSet.getString(NAME));
            user.setSurname(resultSet.getString(SURNAME));
            user.setEmail(resultSet.getString(EMAIL));
            return user;
        } else {
            throw new DaoException(); //TODO
        }
    }
}
