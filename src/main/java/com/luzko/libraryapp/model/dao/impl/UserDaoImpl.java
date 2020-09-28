package com.luzko.libraryapp.model.dao.impl;

import com.luzko.libraryapp.connection.ConnectionPool;
import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.StatementSql;
import com.luzko.libraryapp.model.dao.UserDao;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.model.entity.UserRole;
import com.luzko.libraryapp.model.entity.UserStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                userPassword = resultSet.getString(ColumnName.PASSWORD);
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
    public List<User> findAll() throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            return createUsersFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("dao"); //TODO
        }
    }

    @Override
    public boolean add(String login, String password, UserRole role,
                       String name, String surname, String email, String codeConfirm) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.ADD_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setInt(3, role.getId());
            statement.setString(4, name);
            statement.setString(5, surname);
            statement.setString(6, email);
            statement.setInt(7, UserStatus.UNCONFIRMED.getId());
            statement.setString(8, codeConfirm);
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
    public boolean changeUserStatus(String login, int status) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.CHANGE_USER_STATUS)) {
            statement.setInt(1, status);
            statement.setString(2, login);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("dao", e); //TODO
        }
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        return resultSet != null && resultSet.next() ? createUser(resultSet) : null;
    }

    private List<User> createUsersFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                User user = createUser(resultSet);
                userList.add(user);
            }
        }
        return userList;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getLong(ColumnName.USER_ID));
        user.setLogin(resultSet.getString(ColumnName.LOGIN));
        user.setUserRole(UserRole.getRoleById(resultSet.getInt(ColumnName.ROLE_ID_FK)));
        user.setName(resultSet.getString(ColumnName.NAME));
        user.setSurname(resultSet.getString(ColumnName.SURNAME));
        user.setEmail(resultSet.getString(ColumnName.EMAIL));
        user.setUserStatus(UserStatus.getStatusById(resultSet.getInt(ColumnName.USER_STATUS_ID_FK)));
        return user;
    }
}

