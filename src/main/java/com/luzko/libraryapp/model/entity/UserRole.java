package com.luzko.libraryapp.model.entity;

import java.util.Arrays;
import java.util.Optional;

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

    public static Optional<UserRole> defineRoleById(int id) {
        return Arrays.stream(UserRole.values())
                .filter(userRole -> userRole.defineId() == id)
                .findFirst();
    }
}