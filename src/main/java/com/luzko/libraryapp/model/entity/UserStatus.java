package com.luzko.libraryapp.model.entity;

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

    public static UserStatus defineStatusById(int id) {
        for (UserStatus userStatus : UserStatus.values()) {
            if (userStatus.defineId() == id) {
                return userStatus;
            }
        }
        return null;
    }
}
