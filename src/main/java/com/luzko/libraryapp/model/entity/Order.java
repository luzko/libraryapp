package com.luzko.libraryapp.model.entity;

import com.luzko.libraryapp.model.builder.OrderBuilder;

import java.util.StringJoiner;

public class Order extends BaseEntity {
    private Long orderId;
    private User user;
    private Book book;
    private OrderStatus orderStatus;
    private OrderType orderType;
    private String orderDate;
    private String returnDate;

    public Order(OrderBuilder builder) {
        this.orderId = builder.getOrderId();
        this.user = builder.getUser();
        this.book = builder.getBook();
        this.orderStatus = builder.getOrderStatus();
        this.orderType = builder.getOrderType();
        this.orderDate = builder.getOrderDate();
        this.returnDate = builder.getReturnDate();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        if (orderId != null ? !orderId.equals(order.orderId) : order.orderId != null) {
            return false;
        }
        if (user != null ? !user.equals(order.user) : order.user != null) {
            return false;
        }
        if (book != null ? !book.equals(order.book) : order.book != null) {
            return false;
        }
        if (orderStatus != order.orderStatus || orderType != order.orderType) {
            return false;
        }
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) {
            return false;
        }
        return returnDate != null ? returnDate.equals(order.returnDate) : order.returnDate == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("orderId=" + orderId)
                .add("user=" + user)
                .add("book=" + book)
                .add("orderStatus=" + orderStatus)
                .add("orderType=" + orderType)
                .add("orderDate='" + orderDate + "'")
                .add("returnDate='" + returnDate + "'")
                .toString();
    }
}
