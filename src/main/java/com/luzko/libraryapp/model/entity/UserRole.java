package com.luzko.libraryapp.model.entity;

public enum UserRole {
    ADMIN(LocaleName.ROLE_ADMIN),
    LIBRARIAN(LocaleName.ROLE_LIBRARIAN),
    READER(LocaleName.ROLE_READER);

    private final String localeName;

    UserRole(String localeName) {
        this.localeName = localeName;
    }

    public String getLocaleName() {
        return localeName;
    }

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