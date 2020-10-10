package com.luzko.libraryapp.model.entity.user;

import com.luzko.libraryapp.model.builder.UserBuilder;
import com.luzko.libraryapp.model.entity.BaseEntity;

public class User extends BaseEntity {
    private Long userId;
    private String login;
    private UserRole userRole;
    private String name;
    private String surname;
    private String email;
    private UserStatus userStatus;

    public User() {

    }

    public User(UserBuilder builder) {
        this.userId = builder.getUserId();
        this.login = builder.getLogin();
        this.userRole = builder.getUserRole();
        this.name = builder.getName();
        this.surname = builder.getSurname();
        this.email = builder.getEmail();
        this.userStatus = builder.getUserStatus();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    //TODO equals, hashcode, toString..... now fields change.....
}
