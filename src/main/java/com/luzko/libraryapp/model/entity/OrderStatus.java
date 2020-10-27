package com.luzko.libraryapp.model.entity;

import java.util.Arrays;
import java.util.Optional;

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

    public static Optional<OrderStatus> defineOrderStatusById(int id) {
        return Arrays.stream(OrderStatus.values())
                .filter(orderStatus -> orderStatus.defineId() == id)
                .findFirst();
    }
}
