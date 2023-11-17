package com.nfb.modules.stakeholders.core.domain.user;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserRole role;

    // Constructors, getters, and setters

    public User() {
    }

    public User(String username, String password, UserRole role) {
        super();
        this.username = username;
        this.password = password;
        this.role = role;
        validate();
    }


    // Getters for other fields

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setRole(UserRole role) {
        this.role = role;
    }

    // Validation method
    private void validate() {
        if (this.username == null || this.username.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Username");
        }
        if (this.password == null || this.password.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Password");
        }
    }
}
