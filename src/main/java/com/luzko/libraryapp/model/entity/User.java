package com.luzko.libraryapp.model.entity;

import java.util.StringJoiner;

public class User extends BaseEntity {
    private Long userId;
    private String login;
    private UserRole userRole;
    private String name;
    private String surname;
    private String email;
    private boolean enabled;

    public User() {

    }

    public User(Long userId, String login, UserRole userRole, String name, String surname, String email) {
        this.userId = userId;
        this.login = login;
        this.userRole = userRole;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.enabled = true;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        User user = (User) o;

        if (userId != user.userId || enabled != user.enabled || !login.equals(user.login) ||
                userRole != user.userRole || !name.equals(user.name) || !surname.equals(user.surname)) {
            return false;
        }

        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + login.hashCode();
        result = 31 * result + userRole.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("login='" + login + "'")
                .add("userRole=" + userRole)
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("email='" + email + "'")
                .add("enabled=" + enabled)
                .toString();
    }
}
