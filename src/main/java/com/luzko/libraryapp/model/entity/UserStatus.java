package com.luzko.libraryapp.model.entity;

public enum UserStatus {
    ACTIVE(1),
    BLOCKED(2),
    UNCONFIRMED(3);

    private final int id;

    UserStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserStatus getStatusById(int id) {
        for (UserStatus userStatus : UserStatus.values()) {
            if (userStatus.id == id) {
                return userStatus;
            }
        }
        //LOGGER.warn(String.format("Role with id: %d is not found", id));
        throw new EnumConstantNotPresentException(UserRole.class, String.format("Role with id: %d is not found", id));
        //throw new Exception(""); //TODO
        //TODO !!!!!!! change
    }
}
