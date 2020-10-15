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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private static final int MAX_COUNT_NEW_ORDER = 2;

    @Override
    public Optional<Order> findById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_ALL_ORDERS)) {
            ResultSet resultSet = statement.executeQuery();
            return createOrdersFromResultSet(resultSet, ColumnName.ALL);
        } catch (SQLException e) {
            throw new DaoException("Find all error", e);
        }
    }

    @Override
    public List<Order> findNew() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_NEW_ORDERS)) {
            ResultSet resultSet = statement.executeQuery();
            return createOrdersFromResultSet(resultSet, ColumnName.NEW);
        } catch (SQLException e) {
            throw new DaoException("Find new error", e);
        }
    }

    @Override
    public List<Order> findByUserId(long userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_ORDERS_BY_USER_ID)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            return createOrdersFromResultSet(resultSet, ColumnName.USER);
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
            return createOrdersFromResultSet(resultSet, ColumnName.BOOK);
        } catch (SQLException e) {
            throw new DaoException("Find by id error", e);
        }
    }

    @Override
    public boolean isCancel(long orderId) throws DaoException {
        return isChangeStatus(orderId, OrderStatus.CANCELED.defineId());
    }

    @Override
    public boolean isDeny(long orderId) throws DaoException {
        return isChangeStatus(orderId, OrderStatus.DENIED.defineId());
    }

    @Override
    public boolean isReturn(long orderId, long bookId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean isReturnBook = false;
        try (PreparedStatement statementReturnOrder = connection.prepareStatement(StatementSql.RETURN_ORDER);
             PreparedStatement statementReturnBook = connection.prepareStatement(StatementSql.RETURN_BOOK)) {
            connectionSetAutoCommit(connection, false);
            if (isOrderReturn(statementReturnOrder, orderId)) {
                statementReturnBook.setLong(1, bookId);
                isReturnBook = statementReturnBook.executeUpdate() == 1;
            }
            connectionCommitChanges(connection);
            return isReturnBook;
        } catch (SQLException e) {
            connectionsRollback(connection);
            throw new DaoException("Order create error", e);
        } finally {
            connectionSetAutoCommit(connection, true);
            closeConnection(connection);
        }
    }

    @Override
    public boolean isCreateOrder(long userId, long bookId, OrderType orderType) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean isCreateOrder = false;
        try (PreparedStatement statementCountBook = connection.prepareStatement(StatementSql.COUNT_BOOK_BY_ID);
             PreparedStatement statementCountOrders = connection.prepareStatement(StatementSql.COUNT_ORDERS_BY_ID);
             PreparedStatement statementCreateOrder = connection.prepareStatement(StatementSql.CREATE_ORDER)) {
            connectionSetAutoCommit(connection, false);
            int countBookById = countBookById(statementCountBook, bookId);
            if (countBookById > 0) {
                if (countOrderByUser(statementCountOrders, userId) < MAX_COUNT_NEW_ORDER) {
                    statementCreateOrder.setLong(1, userId);
                    statementCreateOrder.setLong(2, bookId);
                    statementCreateOrder.setInt(3, orderType.defineId());
                    statementCreateOrder.setLong(4, DateUtil.defineCountMillisecondsFromNow());
                    isCreateOrder = statementCreateOrder.executeUpdate() == 1;
                }
            }
            connectionCommitChanges(connection);
            return isCreateOrder;
        } catch (SQLException e) {
            connectionsRollback(connection);
            throw new DaoException("Order create error", e);
        } finally {
            connectionSetAutoCommit(connection, true);
            closeConnection(connection);
        }
    }


    private int countBookById(PreparedStatement statement, long bookId) throws SQLException {
        statement.setLong(1, bookId);
        ResultSet resultSet = statement.executeQuery();
        return resultSet != null && resultSet.next() ? resultSet.getInt(ColumnName.NUMBER) : 0;
    }

    private int countOrderByUser(PreparedStatement statement, long useId) throws SQLException {
        statement.setLong(1, useId);
        ResultSet resultSet = statement.executeQuery();
        return resultSet != null && resultSet.next() ? resultSet.getInt(ColumnName.COUNT) : MAX_COUNT_NEW_ORDER;
    }

    private List<Order> createOrdersFromResultSet(ResultSet resultSet, String typeView) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                Optional<Order> orderOptional = createOrderView(resultSet, typeView);
                orderOptional.ifPresent(orderList::add);
            }
        }
        return orderList;
    }

    private Optional<Order> createOrderView(ResultSet resultSet, String typeView) throws SQLException {
        switch (typeView) {
            case ColumnName.BOOK -> {
                return createOrderBookId(resultSet);
            }
            case ColumnName.USER -> {
                return createOrderUserId(resultSet);
            }
            case ColumnName.NEW -> {
                return createNewOrder(resultSet);
            }
            case ColumnName.ALL -> {
                return createOrder(resultSet);
            }
            default -> {
                return Optional.empty();
            }
        }
    }

    private Optional<Order> createOrder(ResultSet resultSet) throws SQLException {
        OrderBuilder orderBuilder = createBaseOrder(resultSet);
        return fillReturnOrder(resultSet, orderBuilder);
    }

    private Optional<Order> createNewOrder(ResultSet resultSet) throws SQLException {
        OrderBuilder orderBuilder = createBaseOrder(resultSet);
        Order order = new Order(orderBuilder);
        Optional<Order> orderOptional = Optional.empty();
        if (order.getOrderStatus() != null && order.getOrderType() != null) {
            orderOptional = Optional.of(order);
        }
        return orderOptional;
    }

    private Optional<Order> createOrderUserId(ResultSet resultSet) throws SQLException {
        BookBuilder bookBuilder = new BookBuilder()
                .setBookId(resultSet.getLong(ColumnName.BOOK_ID))
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
        return fillReturnOrder(resultSet, orderBuilder);
    }

    private OrderBuilder createBaseOrder(ResultSet resultSet) throws SQLException {
        BookBuilder bookBuilder = new BookBuilder()
                .setTitle(resultSet.getString(ColumnName.BOOK_TITLE));
        Book book = new Book(bookBuilder);
        UserBuilder userBuilder = new UserBuilder()
                .setLogin(resultSet.getString(ColumnName.USER_LOGIN));
        User user = new User(userBuilder);
        return new OrderBuilder()
                .setOrderId(resultSet.getLong(ColumnName.ORDER_ID))
                .setOrderStatus(OrderStatus.defineOrderStatusById(resultSet.getInt(ColumnName.ORDER_STATUS)))
                .setOrderType(OrderType.defineOrderTypeById(resultSet.getInt(ColumnName.ORDER_TYPE)))
                .setOrderDate(DateUtil.defineDateValue(resultSet.getLong(ColumnName.ORDER_DATE)))
                .setUser(user)
                .setBook(book);
    }

    private Optional<Order> fillReturnOrder(ResultSet resultSet, OrderBuilder orderBuilder) throws SQLException {
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

    private boolean isOrderReturn(PreparedStatement statement, long orderId) throws SQLException {
        statement.setLong(1, DateUtil.defineCountMillisecondsFromNow());
        statement.setLong(2, orderId);
        return statement.executeUpdate() == 1;
    }

    private boolean isChangeStatus(long orderId, int status) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.CHANGE_STATUS_ORDER)) {
            statement.setInt(1, status);
            statement.setLong(2, orderId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("Cancel order error", e);
        }
    }
}
