package com.luzko.libraryapp.model.entity.order;

public enum OrderType {
    READING_ROOM,
    HOME;

    public int defineId() {
        return this.ordinal() + 1;
    }

    public static OrderType defineOrderTypeById(int id) {
        for (OrderType orderType : OrderType.values()) {
            if (orderType.defineId() == id) {
                return orderType;
            }
        }
        return null;
    }
}
