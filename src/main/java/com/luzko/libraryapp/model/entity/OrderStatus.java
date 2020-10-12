package com.luzko.libraryapp.model.entity;


public enum OrderStatus {
    NEW,
    APPROVED,
    DENIED,
    CANCELED;

    public int defineId() {
        return this.ordinal() + 1;
    }

    public static OrderStatus defineOrderStatusById(int id) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.defineId() == id) {
                return orderStatus;
            }
        }
        return null;
    }
}
