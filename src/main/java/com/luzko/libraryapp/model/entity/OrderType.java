package com.luzko.libraryapp.model.entity;

public enum OrderType {
    READING_ROOM(LocaleName.TYPE_READING_ROOM),
    HOME(LocaleName.TYPE_HOME);

    private final String localeName;

    OrderType(String localeName) {
        this.localeName = localeName;
    }

    public String getLocaleName() {
        return localeName;
    }

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
