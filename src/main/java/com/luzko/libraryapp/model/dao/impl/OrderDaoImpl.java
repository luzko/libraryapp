package com.luzko.libraryapp.model.dao.impl;

import com.luzko.libraryapp.connection.ConnectionPool;
import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.builder.BookBuilder;
import com.luzko.libraryapp.model.builder.OrderBuilder;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.OrderDao;
import com.luzko.libraryapp.model.dao.StatementSql;
import com.luzko.libraryapp.model.entity.Book;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.model.entity.OrderStatus;
import com.luzko.libraryapp.model.entity.OrderType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Optional<Order> findById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() throws DaoException {
        return null;
    }

    @Override
    public List<Order> findByUserId(long userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_ORDERS_BY_USER_ID)) {
            ResultSet resultSet = statement.executeQuery();
            return createOrdersUserIdFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find all error", e);
        }
    }

    private List<Order> createOrdersUserIdFromResultSet(ResultSet resultSet) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                Optional<Order> orderOptional = createOrderUserId(resultSet);
                orderOptional.ifPresent(orderList::add);
            }
        }
        return orderList;
    }

    private Optional<Order> createOrderUserId(ResultSet resultSet) throws SQLException {

        BookBuilder bookBuilder = new BookBuilder()
                .setTitle(resultSet.getString(ColumnName.BOOK_TITLE));
        Book book = new Book(bookBuilder);
        OrderBuilder orderBuilder = new OrderBuilder()
                .setOrderId(resultSet.getLong(ColumnName.ORDER_ID))
                .setOrderStatus(OrderStatus.defineOrderStatusById(resultSet.getInt(ColumnName.ORDER_STATUS)))
                .setOrderType(OrderType.defineOrderTypeById(resultSet.getInt(ColumnName.ORDER_TYPE)))
                .setBook(book);
        //.setOrderDate()

        return null;
    }
}



/*
    private Optional<User> createUser(ResultSet resultSet) throws SQLException {
        UserBuilder userBuilder = new UserBuilder()
                .setUserId(resultSet.getLong(ColumnName.USER_ID))
                .setLogin(resultSet.getString(ColumnName.LOGIN))
                .setUserRole(UserRole.defineRoleById(resultSet.getInt(ColumnName.ROLE_ID_FK)))
                .setName(resultSet.getString(ColumnName.NAME))
                .setSurname(resultSet.getString(ColumnName.SURNAME))
                .setEmail(resultSet.getString(ColumnName.EMAIL))
                .setUserStatus(UserStatus.defineStatusById(resultSet.getInt(ColumnName.USER_STATUS_ID_FK)));
        User user = new User(userBuilder);
        Optional<User> userOptional = Optional.empty();
        if (user.getUserRole() != null && user.getUserStatus() != null) {
            userOptional = Optional.of(user);
        }
        return userOptional;
    }
 */
