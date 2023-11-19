package com.nfb.modules.stakeholders.API.dtos;

import com.nfb.modules.stakeholders.core.domain.user.User;
import com.nfb.modules.stakeholders.core.domain.user.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;

public class UserDTO {

    @Schema(description = "User ID")
    private long id;

    @Schema(description = "Username")
    private String username;

    // You might want to hide sensitive information like passwords from Swagger
    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY, description = "Password")
    private String password;

    @Schema(description = "User Role")
    private UserRole role;

    public UserDTO(long id, String username, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
