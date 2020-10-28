package com.luzko.libraryapp.model.dao.impl;

import com.luzko.libraryapp.model.connection.ConnectionPool;
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

/**
 * The type Order dao.
 */
public class OrderDaoImpl implements OrderDao {
    private static final int MAX_COUNT_NEW_ORDER = 2;
    private static final OrderDao INSTANCE = new OrderDaoImpl();

    private OrderDaoImpl() {

    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Order> findPartNew(int shownRecords, int recordsPerPage) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_NEW_ORDERS)) {
            statement.setInt(1, recordsPerPage);
            statement.setInt(2, shownRecords);
            ResultSet resultSet = statement.executeQuery();
            return createOrdersFromResultSet(resultSet, ColumnName.NEW);
        } catch (SQLException e) {
            throw new DaoException("Find new error", e);
        }
    }

    @Override
    public List<Order> findPartOfAll(int shownRecords, int recordsPerPage) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_PART_ORDERS)) {
            statement.setInt(1, recordsPerPage);
            statement.setInt(2, shownRecords);
            ResultSet resultSet = statement.executeQuery();
            return createOrdersFromResultSet(resultSet, ColumnName.ALL);
        } catch (SQLException e) {
            throw new DaoException("Find all error", e);
        }
    }

    @Override
    public List<Order> findPartByUserId(long userId, int shownRecords, int recordsPerPage) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_ORDERS_BY_USER_ID)) {
            statement.setLong(1, userId);
            statement.setInt(2, recordsPerPage);
            statement.setInt(3, shownRecords);
            ResultSet resultSet = statement.executeQuery();
            return createOrdersFromResultSet(resultSet, ColumnName.USER);
        } catch (SQLException e) {
            throw new DaoException("Find by id error", e);
        }
    }

    @Override
    public List<Order> findPartByBookId(long bookId, int shownRecords, int recordsPerPage) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_ORDER_BY_BOOK_ID)) {
            statement.setLong(1, bookId);
            statement.setInt(2, recordsPerPage);
            statement.setInt(3, shownRecords);
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
            setAutoCommit(connection, false);
            if (isOrderReturn(statementReturnOrder, orderId)) {
                statementReturnBook.setLong(1, bookId);
                isReturnBook = statementReturnBook.executeUpdate() == 1;
            }
            commit(connection);
            return isReturnBook;
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException("Order return error", e);
        } finally {
            setAutoCommit(connection, true);
            close(connection);
        }
    }

    @Override
    public boolean isApprove(long orderId, long bookId, long userId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean isApproveBook = false;
        try (PreparedStatement statementCountBook = connection.prepareStatement(StatementSql.COUNT_BOOK_BY_ID);
             PreparedStatement statementCountOrders = connection.prepareStatement(StatementSql.COUNT_ORDERS_BY_ID);
             PreparedStatement statementApproveBook = connection.prepareStatement(StatementSql.APPROVE_BOOK);
             PreparedStatement statementApproveOrder = connection.prepareStatement(StatementSql.CHANGE_STATUS_ORDER)) {
            setAutoCommit(connection, false);
            int countBookById = countBookById(statementCountBook, bookId);
            int countOrderByUser = countOrderByUser(statementCountOrders, userId);
            if (countBookById > 0 && countOrderByUser < MAX_COUNT_NEW_ORDER &&
                    isChangeOrderStatus(statementApproveOrder, orderId, OrderStatus.APPROVED.defineId())) {
                statementApproveBook.setLong(1, bookId);
                isApproveBook = statementApproveBook.executeUpdate() == 1;
            }
            commit(connection);
            return isApproveBook;
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException("Order Approve error", e);
        } finally {
            setAutoCommit(connection, true);
            close(connection);
        }
    }

    @Override
    public boolean isCreateOrder(long userId, long bookId, OrderType orderType) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean isCreateOrder = false;
        try (PreparedStatement statementCountBook = connection.prepareStatement(StatementSql.COUNT_BOOK_BY_ID);
             PreparedStatement statementCountOrders = connection.prepareStatement(StatementSql.COUNT_ORDERS_BY_ID);
             PreparedStatement statementCreateOrder = connection.prepareStatement(StatementSql.CREATE_ORDER)) {
            setAutoCommit(connection, false);
            int countBookById = countBookById(statementCountBook, bookId);
            if (countBookById > 0 && countOrderByUser(statementCountOrders, userId) < MAX_COUNT_NEW_ORDER) {
                statementCreateOrder.setLong(1, userId);
                statementCreateOrder.setLong(2, bookId);
                statementCreateOrder.setInt(3, orderType.defineId());
                statementCreateOrder.setLong(4, DateUtil.defineCountMillisecondsFromNow());
                isCreateOrder = statementCreateOrder.executeUpdate() == 1;
            }
            commit(connection);
            return isCreateOrder;
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException("Order create error", e);
        } finally {
            setAutoCommit(connection, true);
            close(connection);
        }
    }

    @Override
    public int findCountByUserId(long userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_COUNT_ORDERS_BY_USER)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(ColumnName.COUNT);
        } catch (SQLException e) {
            throw new DaoException("Find count record by user", e);
        }
    }

    @Override
    public int findCountByBookId(long bookId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_COUNT_ORDERS_BY_BOOK)) {
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(ColumnName.COUNT);
        } catch (SQLException e) {
            throw new DaoException("Find count record by book", e);
        }
    }

    @Override
    public int findCountNew() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_COUNT_NEW_ORDERS)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(ColumnName.COUNT);
        } catch (SQLException e) {
            throw new DaoException("Find count new order", e);
        }
    }

    @Override
    public int findCountAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.FIND_COUNT_ALL_ORDERS)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(ColumnName.COUNT);
        } catch (SQLException e) {
            throw new DaoException("Find count all order", e);
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
        return switch (typeView) {
            case ColumnName.BOOK -> createOrderBookId(resultSet);
            case ColumnName.USER -> createOrderUserId(resultSet);
            case ColumnName.NEW -> createNewOrder(resultSet);
            case ColumnName.ALL -> createOrder(resultSet);
            default -> Optional.empty();
        };
    }

    private Optional<Order> createOrder(ResultSet resultSet) throws SQLException {
        Optional<OrderBuilder> builderOptional = createBaseOrder(resultSet);
        return builderOptional.isPresent() ? fillReturnOrder(resultSet, builderOptional.get()) : Optional.empty();
    }

    private Optional<Order> createNewOrder(ResultSet resultSet) throws SQLException {
        Optional<OrderBuilder> builderOptional = createBaseOrder(resultSet);
        return builderOptional.map(Order::new);
    }

    private Optional<Order> createOrderUserId(ResultSet resultSet) throws SQLException {
        Optional<Order> orderOptional = Optional.empty();
        Optional<OrderStatus> orderStatusOptional = OrderStatus.defineOrderStatusById(resultSet.getInt(ColumnName.ORDER_STATUS));
        Optional<OrderType> orderTypeOptional = OrderType.defineOrderTypeById(resultSet.getInt(ColumnName.ORDER_TYPE));
        if (orderStatusOptional.isPresent() && orderTypeOptional.isPresent()) {
            BookBuilder bookBuilder = new BookBuilder()
                    .setBookId(resultSet.getLong(ColumnName.BOOK_ID))
                    .setTitle(resultSet.getString(ColumnName.BOOK_TITLE));
            Book book = new Book(bookBuilder);
            OrderBuilder orderBuilder = new OrderBuilder()
                    .setOrderId(resultSet.getLong(ColumnName.ORDER_ID))
                    .setOrderStatus(orderStatusOptional.get())
                    .setOrderType(orderTypeOptional.get())
                    .setOrderDate(DateUtil.defineDateValue(resultSet.getLong(ColumnName.ORDER_DATE)))
                    .setBook(book);
            Order order = new Order(orderBuilder);
            orderOptional = Optional.of(order);
        }
        return orderOptional;
    }

    private Optional<Order> createOrderBookId(ResultSet resultSet) throws SQLException {
        Optional<Order> orderOptional = Optional.empty();
        Optional<OrderStatus> orderStatusOptional = OrderStatus.defineOrderStatusById(resultSet.getInt(ColumnName.ORDER_STATUS));
        Optional<OrderType> orderTypeOptional = OrderType.defineOrderTypeById(resultSet.getInt(ColumnName.ORDER_TYPE));
        if (orderStatusOptional.isPresent() && orderTypeOptional.isPresent()) {
            UserBuilder userBuilder = new UserBuilder()
                    .setLogin(resultSet.getString(ColumnName.USER_LOGIN));
            User user = new User(userBuilder);
            OrderBuilder orderBuilder = new OrderBuilder()
                    .setOrderId(resultSet.getLong(ColumnName.ORDER_ID))
                    .setOrderStatus(orderStatusOptional.get())
                    .setOrderType(orderTypeOptional.get())
                    .setOrderDate(DateUtil.defineDateValue(resultSet.getLong(ColumnName.ORDER_DATE)))
                    .setUser(user);
            orderOptional = fillReturnOrder(resultSet, orderBuilder);
        }
        return orderOptional;
    }

    private Optional<OrderBuilder> createBaseOrder(ResultSet resultSet) throws SQLException {
        Optional<OrderBuilder> orderBuilderOptional = Optional.empty();
        Optional<OrderStatus> orderStatusOptional = OrderStatus.defineOrderStatusById(resultSet.getInt(ColumnName.ORDER_STATUS));
        Optional<OrderType> orderTypeOptional = OrderType.defineOrderTypeById(resultSet.getInt(ColumnName.ORDER_TYPE));
        if (orderStatusOptional.isPresent() && orderTypeOptional.isPresent()) {
            BookBuilder bookBuilder = new BookBuilder()
                    .setBookId(resultSet.getLong(ColumnName.BOOK_ID))
                    .setTitle(resultSet.getString(ColumnName.BOOK_TITLE));
            Book book = new Book(bookBuilder);
            UserBuilder userBuilder = new UserBuilder()
                    .setUserId(resultSet.getLong(ColumnName.USER_ID))
                    .setLogin(resultSet.getString(ColumnName.USER_LOGIN));
            User user = new User(userBuilder);
            orderBuilderOptional = Optional.of(new OrderBuilder()
                    .setOrderId(resultSet.getLong(ColumnName.ORDER_ID))
                    .setOrderStatus(orderStatusOptional.get())
                    .setOrderType(orderTypeOptional.get())
                    .setOrderDate(DateUtil.defineDateValue(resultSet.getLong(ColumnName.ORDER_DATE)))
                    .setUser(user)
                    .setBook(book));
        }
        return orderBuilderOptional;
    }

    private Optional<Order> fillReturnOrder(ResultSet resultSet, OrderBuilder orderBuilder) throws SQLException {
        long millisecondsReturn = resultSet.getLong(ColumnName.RETURN_DATE);
        if (millisecondsReturn != 0) {
            orderBuilder.setReturnDate(DateUtil.defineDateValue(millisecondsReturn));
        } else {
            orderBuilder.setReturnDate("-");
        }
        Order order = new Order(orderBuilder);
        return Optional.of(order);
    }

    private boolean isOrderReturn(PreparedStatement statement, long orderId) throws SQLException {
        statement.setLong(1, DateUtil.defineCountMillisecondsFromNow());
        statement.setLong(2, orderId);
        return statement.executeUpdate() == 1;
    }

    private boolean isChangeStatus(long orderId, int status) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(StatementSql.CHANGE_STATUS_ORDER)) {
            return isChangeOrderStatus(statement, orderId, status);
        } catch (SQLException e) {
            throw new DaoException("Cancel order error", e);
        }
    }

    private boolean isChangeOrderStatus(PreparedStatement statement, long orderId, int status) throws SQLException {
        statement.setInt(1, status);
        statement.setLong(2, orderId);
        return statement.executeUpdate() == 1;
    }
}
