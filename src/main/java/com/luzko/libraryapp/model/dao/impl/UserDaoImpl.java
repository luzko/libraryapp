package com.luzko.libraryapp.model.dao.impl;

import com.luzko.libraryapp.model.builder.UserBuilder;
import com.luzko.libraryapp.connection.ConnectionPool;
import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.StatementSql;
import com.luzko.libraryapp.model.dao.UserDao;
import com.luzko.libraryapp.model.entity.*;
import com.luzko.libraryapp.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public String findPasswordByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_PASSWORD_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            String userPassword = null;
            if (resultSet.next()) {
                userPassword = resultSet.getString(ColumnName.PASSWORD);
            }
            return userPassword;
        } catch (SQLException e) {
            throw new DaoException("Find password error", e);
        }
    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return createUserFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find by id error", e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            return createUserFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find by login error", e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            return createUsersFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find all error", e);
        }
    }

    @Override
    public boolean add(User user, String password, String codeConfirm) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.ADD_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, password);
            statement.setInt(3, user.getUserRole().defineId());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setString(6, user.getEmail());
            statement.setInt(7, UserStatus.UNCONFIRMED.defineId());
            statement.setString(8, codeConfirm);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Add error", e);
        }
    }

    @Override
    public boolean isLoginUnique(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_COUNT_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(ColumnName.COUNT);
            return count == 0;
        } catch (SQLException e) {
            throw new DaoException("Login unique error", e);
        }
    }

    @Override
    public String findCodeConfirmByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_CODE_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            String confirmCode = null;
            if (resultSet.next()) {
                confirmCode = resultSet.getString(ColumnName.CONFIRM_CODE);
            }
            return confirmCode;
        } catch (SQLException e) {
            throw new DaoException("Find code confirm error", e);
        }
    }

    @Override
    public boolean changeUserLogin(String login, String newLogin) throws DaoException {
        try {
            return changeUserAttribute(login, newLogin, StatementSql.CHANGE_USER_LOGIN);
        } catch (
                SQLException e) {
            throw new DaoException("Change user login error", e);
        }
    }

    @Override
    public boolean changeUserName(String login, String newName) throws DaoException {
        try {
            return changeUserAttribute(login, newName, StatementSql.CHANGE_USER_NAME);
        } catch (SQLException e) {
            throw new DaoException("Change user name error", e);
        }
    }

    @Override
    public boolean changeUserSurname(String login, String newSurname) throws DaoException {
        try {
            return changeUserAttribute(login, newSurname, StatementSql.CHANGE_USER_SURNAME);
        } catch (SQLException e) {
            throw new DaoException("Change user surname error", e);
        }
    }

    @Override
    public void giveBooksFromReadingRoom(long userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.GIVE_ALL_BOOK)) {
            statement.setInt(1, OrderStatus.RETURNED.defineId());
            statement.setLong(2, DateUtil.defineCountMillisecondsFromNow());
            statement.setLong(3, userId);
            statement.setInt(4, OrderStatus.APPROVED.defineId());
            statement.setInt(5, OrderType.READING_ROOM.defineId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Change user status error", e);
        }
    }

    private boolean changeUserAttribute(String login, String attribute, String sqlQuery) throws SQLException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, attribute);
            statement.setString(2, login);
            return statement.executeUpdate() == 1;
        }
    }

    @Override
    public boolean changeUserStatus(String login, int status) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.CHANGE_USER_STATUS)) {
            statement.setInt(1, status);
            statement.setString(2, login);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("Change user status error", e);
        }
    }

    private Optional<User> createUserFromResultSet(ResultSet resultSet) throws SQLException {
        return resultSet != null && resultSet.next() ? createUser(resultSet) : Optional.empty();
    }

    private List<User> createUsersFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                Optional<User> userOptional = createUser(resultSet);
                userOptional.ifPresent(userList::add);
            }
        }
        return userList;
    }

    private Optional<User> createUser(ResultSet resultSet) throws SQLException {
        UserBuilder userBuilder = new UserBuilder()
                .setUserId(resultSet.getLong(ColumnName.USER_ID))
                .setLogin(resultSet.getString(ColumnName.LOGIN))
                .setUserRole(UserRole.defineRoleById(resultSet.getInt(ColumnName.ROLE_ID_FK)))
                .setName(resultSet.getString(ColumnName.NAME))
                .setSurname(resultSet.getString(ColumnName.SURNAME))
                .setEmail(resultSet.getString(ColumnName.EMAIL))
                .setUserStatus(UserStatus.defineStatusById(resultSet.getInt(ColumnName.USER_STATUS_ID_FK)))
                .setUserAvatar(resultSet.getString(ColumnName.AVATAR));
        User user = new User(userBuilder);
        Optional<User> userOptional = Optional.empty();
        if (user.getUserRole() != null && user.getUserStatus() != null) {
            userOptional = Optional.of(user);
        }
        return userOptional;
    }
}