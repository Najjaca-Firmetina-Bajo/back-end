package com.nfb.modules.stakeholders.API.dtos;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;

public class CompanyAdministratorDto {
    @Schema(description = "Company administrator ID")
    private long id;
    @Schema(description = "Company administrator username")
    private String username;
    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY, description = "Company administrator password")
    private String password;
    @Schema(description = "Company administrator role")
    private UserRole role;
    @Schema(description = "Company")
    private Company company;

    public CompanyAdministratorDto(long id, String username, String password, UserRole role, Company company) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.company = company;
    }

    public CompanyAdministratorDto(CompanyAdministrator administrator) {
        this.id = administrator.getId();
        this.password = administrator.getPassword();
        this.username = administrator.getUsername();
        this.role = administrator.getRole();
        this.company = administrator.getCompany();
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
