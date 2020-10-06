package com.luzko.libraryapp.model.entity.user;

public enum UserRole {
    ADMIN,
    LIBRARIAN,
    READER;

    public int defineId() {
        return this.ordinal() + 1;
    }

    public static UserRole defineRoleById(int id) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.defineId() == id) {
                return userRole;
            }
        }
        return null;
    }
}