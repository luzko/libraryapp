package com.luzko.libraryapp.model.entity;

import java.util.Arrays;
import java.util.Optional;

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

    public static Optional<OrderType> defineOrderTypeById(int id) {
        return Arrays.stream(OrderType.values())
                .filter(orderType -> orderType.defineId() == id)
                .findFirst();
    }
}
