package com.nfb.modules.stakeholders.core.domain.user;



import javax.management.relation.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")  // Specify the table name
public class User extends com.nfb.buildingblocks.core.domain.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private Role role;

    // Constructors, getters, and setters

    public User() {
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        validate();
    }

    // Getters and setters for other fields

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters for other fields

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
