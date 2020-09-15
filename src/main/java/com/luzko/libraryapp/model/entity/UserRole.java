package com.luzko.libraryapp.model.entity;

public enum UserRole {
    ADMIN(1),
    LIBRARIAN(2),
    READER(3),
    GUEST(4);

    private final int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserRole getRoleById(int id) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.id == id) {
                return userRole;
            }
        }
        //LOGGER.warn(String.format("Role with id: %d is not found", id));
        throw new EnumConstantNotPresentException(UserRole.class, String.format("Role with id: %d is not found", id));
        //throw new Exception(""); //TODO
        //TODO !!!!!!! change
    }
}