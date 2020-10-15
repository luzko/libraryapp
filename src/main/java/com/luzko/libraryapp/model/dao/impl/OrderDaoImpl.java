package com.luzko.libraryapp.model.dao.impl;

import com.luzko.libraryapp.connection.ConnectionPool;
import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.builder.BookBuilder;
import com.luzko.libraryapp.model.builder.OrderBuilder;
import com.luzko.libraryapp.model.builder.UserBuilder;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.OrderDao;
import com.luzko.libraryapp.model.dao.StatementSql;
import com.luzko.libraryapp.model.entity.*;
import com.luzko.libraryapp.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_ALL_ORDERS)) {
            ResultSet resultSet = statement.executeQuery();
            return createOrdersFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find all error", e);
        }
    }

    @Override
    public List<Order> findByUserId(long userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_ORDERS_BY_USER_ID)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            return createOrdersUserIdFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find by id error", e);
        }
    }

    @Override
    public List<Order> findByBookId(long bookId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_ORDER_BY_BOOK_ID)) {
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            return createOrdersBookIdFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find by id error", e);
        }
    }

    @Override
    public boolean isCancel(long orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.CANCEL_ORDER)) {
            statement.setLong(1, orderId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("Cancel order error", e);
        }
    }

    @Override
    public boolean isReturn(long orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.RETURN_ORDER)) {
            statement.setLong(1, DateUtil.defineCountMillisecondsFromNow());
            statement.setLong(2, orderId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("Cancel order error", e);
        }
    }

    private List<Order> createOrdersFromResultSet(ResultSet resultSet) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                Optional<Order> orderOptional = createOrder(resultSet);
                orderOptional.ifPresent(orderList::add);
            }
        }
        return orderList;
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

    private List<Order> createOrdersBookIdFromResultSet(ResultSet resultSet) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                Optional<Order> orderOptional = createOrderBookId(resultSet);
                orderOptional.ifPresent(orderList::add);
            }
        }
        return orderList;
    }

    private Optional<Order> createOrder(ResultSet resultSet) throws SQLException {
        BookBuilder bookBuilder = new BookBuilder()
                .setTitle(resultSet.getString(ColumnName.BOOK_TITLE));
        Book book = new Book(bookBuilder);
        UserBuilder userBuilder = new UserBuilder()
                .setLogin(resultSet.getString(ColumnName.USER_LOGIN));
        User user = new User(userBuilder);
        OrderBuilder orderBuilder = new OrderBuilder()
                .setOrderId(resultSet.getLong(ColumnName.ORDER_ID))
                .setOrderStatus(OrderStatus.defineOrderStatusById(resultSet.getInt(ColumnName.ORDER_STATUS)))
                .setOrderType(OrderType.defineOrderTypeById(resultSet.getInt(ColumnName.ORDER_TYPE)))
                .setOrderDate(DateUtil.defineDateValue(resultSet.getLong(ColumnName.ORDER_DATE)))
                .setUser(user)
                .setBook(book);
        long millisecondsReturn = resultSet.getLong(ColumnName.RETURN_DATE);
        if (millisecondsReturn != 0) {
            orderBuilder.setReturnDate(DateUtil.defineDateValue(millisecondsReturn));
        } else {
            orderBuilder.setReturnDate("-");
        }
        Order order = new Order(orderBuilder);
        Optional<Order> orderOptional = Optional.empty();
        if (order.getOrderStatus() != null && order.getOrderType() != null) {
            orderOptional = Optional.of(order);
        }
        return orderOptional;
    }

    private Optional<Order> createOrderUserId(ResultSet resultSet) throws SQLException {
        BookBuilder bookBuilder = new BookBuilder()
                .setTitle(resultSet.getString(ColumnName.BOOK_TITLE));
        Book book = new Book(bookBuilder);
        OrderBuilder orderBuilder = new OrderBuilder()
                .setOrderId(resultSet.getLong(ColumnName.ORDER_ID))
                .setOrderStatus(OrderStatus.defineOrderStatusById(resultSet.getInt(ColumnName.ORDER_STATUS)))
                .setOrderType(OrderType.defineOrderTypeById(resultSet.getInt(ColumnName.ORDER_TYPE)))
                .setOrderDate(DateUtil.defineDateValue(resultSet.getLong(ColumnName.ORDER_DATE)))
                .setBook(book);
        Order order = new Order(orderBuilder);
        Optional<Order> orderOptional = Optional.empty();
        if (order.getOrderStatus() != null && order.getOrderType() != null) {
            orderOptional = Optional.of(order);
        }
        return orderOptional;
    }

    private Optional<Order> createOrderBookId(ResultSet resultSet) throws SQLException {
        UserBuilder userBuilder = new UserBuilder()
                .setLogin(resultSet.getString(ColumnName.USER_LOGIN));
        User user = new User(userBuilder);
        OrderBuilder orderBuilder = new OrderBuilder()
                .setOrderId(resultSet.getLong(ColumnName.ORDER_ID))
                .setOrderStatus(OrderStatus.defineOrderStatusById(resultSet.getInt(ColumnName.ORDER_STATUS)))
                .setOrderType(OrderType.defineOrderTypeById(resultSet.getInt(ColumnName.ORDER_TYPE)))
                .setOrderDate(DateUtil.defineDateValue(resultSet.getLong(ColumnName.ORDER_DATE)))
                .setUser(user);
        long millisecondsReturn = resultSet.getLong(ColumnName.RETURN_DATE);
        if (millisecondsReturn != 0) {
            orderBuilder.setReturnDate(DateUtil.defineDateValue(millisecondsReturn));
        } else {
            orderBuilder.setReturnDate("-");
        }
        Order order = new Order(orderBuilder);
        Optional<Order> orderOptional = Optional.empty();
        if (order.getOrderStatus() != null && order.getOrderType() != null) {
            orderOptional = Optional.of(order);
        }
        return orderOptional;
    }
}
