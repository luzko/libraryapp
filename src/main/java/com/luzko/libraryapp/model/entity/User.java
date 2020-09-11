package com.luzko.libraryapp.model.entity;

import java.util.Date;
import java.util.Set;
import java.util.StringJoiner;

public class User extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private boolean enabled;
    private Date registered;
    private Set<Role> roles;

    public User() {

    }

    public User(String name, String email, String password, Set<Role> roles) {
        this(name, email, password, true, new Date(), roles);
    }

    public User(String name, String email, String password, boolean enabled, Date registered, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() || !super.equals(o)) {
            return false;
        }

        User user = (User) o;

        if (enabled != user.enabled || !name.equals(user.name) || !email.equals(user.email) ||
                !password.equals(user.password) || !registered.equals(user.registered)) {
            return false;
        }

        return roles.equals(user.roles);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + registered.hashCode();
        result = 31 * result + roles.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .add("password='" + password + "'")
                .add("enabled=" + enabled)
                .add("registered=" + registered)
                .add("roles=" + roles)
                .add("id=" + id)
                .toString();
    }
}
