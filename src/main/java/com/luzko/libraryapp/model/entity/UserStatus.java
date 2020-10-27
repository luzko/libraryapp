package com.luzko.libraryapp.model.entity;

import java.util.Arrays;
import java.util.Optional;

public enum UserStatus {
    ACTIVE(LocaleName.USER_STATUS_ACTIVE),
    BLOCKED(LocaleName.USER_STATUS_BLOCKED),
    UNCONFIRMED(LocaleName.USER_STATUS_UNCONFIRMED);

    private final String localeName;

    UserStatus(String localeName) {
        this.localeName = localeName;
    }

    public String getLocaleName() {
        return localeName;
    }

    public int defineId() {
        return this.ordinal() + 1;
    }

    public static Optional<UserStatus> defineStatusById(int id) {
        return Arrays.stream(UserStatus.values())
                .filter(userStatus -> userStatus.defineId() == id)
                .findFirst();
    }
}