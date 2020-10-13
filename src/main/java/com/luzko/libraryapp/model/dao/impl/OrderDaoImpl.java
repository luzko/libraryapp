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
        return null;
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
                .setOrderDate(DateUtil.defineDateValue(resultSet.getLong(ColumnName.ORDER_DATE)))
                .setBook(book);
        Order order = new Order(orderBuilder);
        Optional<Order> orderOptional = Optional.empty();
        if (order.getOrderStatus() != null && order.getOrderType() != null) {
            orderOptional = Optional.of(order);
        }
        return orderOptional;
    }
}
