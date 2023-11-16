package com.nfb.modules.stakeholders.core.domain.user;

import com.nfb.buildingblocks.core.domain.Entity;

import javax.management.relation.Role;

public class User extends Entity {
    private String username;
    private String password;
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    private void validate() {
        if (this.username == null || this.username.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Name");
        }
        if (this.password == null || this.password.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Surname");
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setRole(Role role) {
        this.role = role;
    }
}

