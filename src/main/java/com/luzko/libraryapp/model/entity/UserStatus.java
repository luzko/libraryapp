package com.luzko.libraryapp.model.entity;

public enum UserStatus {
    ACTIVE,
    BLOCKED,
    UNCONFIRMED;

    public int defineId() {
        return this.ordinal() + 1;
    }

    public static UserStatus defineStatusById(int id) {
        for (UserStatus userStatus : UserStatus.values()) {
            if (userStatus.defineId() == id) {
                return userStatus;
            }
        }
        return null;
    }
}
