package com.luzko.libraryapp.model.builder;

import com.luzko.libraryapp.model.entity.Book;
import com.luzko.libraryapp.model.entity.OrderStatus;
import com.luzko.libraryapp.model.entity.OrderType;
import com.luzko.libraryapp.model.entity.User;

import java.time.LocalDate;

public class OrderBuilder {
    private Long orderId;
    private User user;
    private Book book;
    private OrderStatus orderStatus;
    private OrderType orderType;
    private String orderDate;
    private String returnDate;

    public Long getOrderId() {
        return orderId;
    }

    public OrderBuilder setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public OrderBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public OrderBuilder setBook(Book book) {
        this.book = book;
        return this;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public OrderBuilder setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public OrderBuilder setOrderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderBuilder setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public OrderBuilder setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }
}
