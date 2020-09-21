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
import java.util.Optional;

import static com.luzko.libraryapp.model.dao.ColumnName.*;

public class UserDaoImpl implements UserDao {
    //private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    //private UserDaoImpl() {

    //}

    //public static UserDaoImpl getInstance() {
    //    return INSTANCE;
    //}

    @Override
    public String findPasswordByLogin(String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_PASSWORD_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            String userPassword = null;
            if (resultSet.next()) {
                userPassword = resultSet.getString(PASSWORD);
            }
            return userPassword;
        } catch (SQLException e) {
            throw new DaoException("dao"); //TODO
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            return Optional.of(createUserFromResultSet(resultSet));
        } catch (SQLException e) {
            throw new DaoException("dao"); //TODO
        }
    }

    @Override
    public boolean add(String login, String password, UserRole role,
                       String name, String surname, String email) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.ADD_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setInt(3, role.getId());
            statement.setString(4, name);
            statement.setString(5, surname);
            statement.setString(6, email);
            statement.setBoolean(7, true);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("dao", e); //TODO
        }
    }

    @Override
    public boolean add(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean update(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean remove(long id) throws DaoException {
        return false;
    }

    @Override
    public User findById(long id) throws DaoException {
        return null;
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
            user.setUserRole(UserRole.getRoleById(resultSet.getInt(ROLE_ID_FK)));
            user.setName(resultSet.getString(NAME));
            user.setSurname(resultSet.getString(SURNAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setEnabled(resultSet.getBoolean(ENABLED));
            return user;
        } else {
            throw new DaoException(); //TODO
        }
    }
}
