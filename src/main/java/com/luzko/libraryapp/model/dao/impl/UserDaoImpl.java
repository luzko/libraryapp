package com.luzko.libraryapp.model.dao.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.builder.UserBuilder;
import com.luzko.libraryapp.model.pool.ConnectionPool;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.StatementSql;
import com.luzko.libraryapp.model.dao.UserDao;
import com.luzko.libraryapp.model.entity.OrderStatus;
import com.luzko.libraryapp.model.entity.OrderType;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.model.entity.UserRole;
import com.luzko.libraryapp.model.entity.UserStatus;
import com.luzko.libraryapp.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type represents User DAO implementation.
 */
public class UserDaoImpl implements UserDao {
    private static final UserDao INSTANCE = new UserDaoImpl();
    private static final String PERCENT = "%";

    private UserDaoImpl() {

    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public int findCount() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_COUNT_USER)) {
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
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_COUNT_SEARCH_USER)) {
            statement.setString(1, PERCENT + searchName + PERCENT);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(ColumnName.COUNT);
        } catch (SQLException e) {
            throw new DaoException("Find count records", e);
        }
    }

    @Override
    public List<User> findPartOfAll(int recordsShown, int recordsPerPage) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_PART_USERS)) {
            statement.setInt(1, recordsPerPage);
            statement.setInt(2, recordsShown);
            ResultSet resultSet = statement.executeQuery();
            return createUsersFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find all error", e);
        }
    }

    @Override
    public List<User> findByLogin(String searchName, int recordsShown, int recordsPerPage) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_USERS)) {
            statement.setString(1, PERCENT + searchName + PERCENT);
            statement.setInt(2, recordsPerPage);
            statement.setInt(3, recordsShown);
            ResultSet resultSet = statement.executeQuery();
            return createUsersFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find all error", e);
        }
    }

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
    public boolean isChangePassword(String login, String newPassword) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.CHANGE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setString(2, login);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Change password error", e);
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
    public boolean isChangeUserLogin(String login, String newLogin) throws DaoException {
        try {
            return changeUserAttribute(login, newLogin, StatementSql.CHANGE_USER_LOGIN);
        } catch (
                SQLException e) {
            throw new DaoException("Change user login error", e);
        }
    }

    @Override
    public boolean isChangeUserName(String login, String newName) throws DaoException {
        try {
            return changeUserAttribute(login, newName, StatementSql.CHANGE_USER_NAME);
        } catch (SQLException e) {
            throw new DaoException("Change user name error", e);
        }
    }

    @Override
    public boolean isChangeUserSurname(String login, String newSurname) throws DaoException {
        try {
            return changeUserAttribute(login, newSurname, StatementSql.CHANGE_USER_SURNAME);
        } catch (SQLException e) {
            throw new DaoException("Change user surname error", e);
        }
    }

    @Override
    public boolean isChangeUserAvatar(String login, String newAvatar) throws DaoException {
        try {
            return changeUserAttribute(login, newAvatar, StatementSql.CHANGE_USER_AVATAR);
        } catch (SQLException e) {
            throw new DaoException("Change user avatar error", e);
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

    @Override
    public boolean isChangeUserStatus(String login, int status) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.CHANGE_USER_STATUS)) {
            statement.setInt(1, status);
            statement.setString(2, login);
            return statement.executeUpdate() == 1;
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
        Optional<User> userOptional = Optional.empty();
        Optional<UserRole> userRoleOptional = UserRole.defineRoleById(resultSet.getInt(ColumnName.ROLE_ID_FK));
        Optional<UserStatus> userStatusOptional = UserStatus.defineStatusById(resultSet.getInt(ColumnName.USER_STATUS_ID_FK));
        if (userRoleOptional.isPresent() && userStatusOptional.isPresent()) {
            UserBuilder userBuilder = new UserBuilder()
                    .setUserId(resultSet.getLong(ColumnName.USER_ID))
                    .setLogin(resultSet.getString(ColumnName.LOGIN))
                    .setUserRole(userRoleOptional.get())
                    .setName(resultSet.getString(ColumnName.NAME))
                    .setSurname(resultSet.getString(ColumnName.SURNAME))
                    .setEmail(resultSet.getString(ColumnName.EMAIL))
                    .setUserStatus(userStatusOptional.get())
                    .setUserAvatar(resultSet.getString(ColumnName.AVATAR));
            User user = new User(userBuilder);
            userOptional = Optional.of(user);
        }
        return userOptional;
    }
}