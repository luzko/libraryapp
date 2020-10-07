package com.luzko.libraryapp.builder;

import com.luzko.libraryapp.model.entity.user.UserRole;
import com.luzko.libraryapp.model.entity.user.UserStatus;

public class UserBuilder {
    private Long userId;
    private String login;
    private UserRole userRole;
    private String name;
    private String surname;
    private String email;
    private UserStatus userStatus;

    public Long getUserId() {
        return userId;
    }

    public UserBuilder setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public UserBuilder setUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public UserBuilder setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
        return this;
    }
}
