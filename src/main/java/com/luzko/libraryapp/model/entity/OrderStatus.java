package com.luzko.libraryapp.model.entity;


public enum OrderStatus {
    NEW(LocaleName.ORDER_STATUS_NEW),
    APPROVED(LocaleName.ORDER_STATUS_APPROVED),
    DENIED(LocaleName.ORDER_STATUS_DENIED),
    CANCELED(LocaleName.ORDER_STATUS_CANCELED),
    RETURNED(LocaleName.ORDER_STATUS_RETURNED);

    private final String localeName;

    OrderStatus(String localeName) {
        this.localeName = localeName;
    }

    public String getLocaleName() {
        return localeName;
    }

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
