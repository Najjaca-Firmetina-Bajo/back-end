package com.nfb.modules.stakeholders.API.dtos;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;

public class CompanyAdministratorDto {
    @Schema(description = "CompanyAdministrator ID")
    private long id;

    @Schema(description = "Email")
    private String email;

    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY, description = "Password")
    private String password;

    @Schema(description = "Role")
    private UserRole role;

    @Schema(description = "CompanyAdministrator's Name")
    private String name;

    @Schema(description = "CompanyAdministrator's Surname")
    private String surname;

    @Schema(description = "CompanyAdministrator's City")
    private String city;

    @Schema(description = "CompanyAdministrator's Country")
    private String country;

    @Schema(description = "CompanyAdministrator's Phone Number")
    private String phoneNumber;

    @Schema(description = "CompanyAdministrator's Occupation")
    private String occupation;

    @Schema(description = "CompanyAdministrator's Company Information")
    private String companyInfo;

    @Schema(description = "Activation Status")
    private boolean activated;

    //@Schema(description = "Company")
    //private Company company;

    public CompanyAdministratorDto(long id, String email, String password, UserRole role, String name, String surname, String city, String country, String phoneNumber, String occupation, String companyInfo, boolean activated) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.occupation = occupation;
        this.companyInfo = companyInfo;
        this.activated = activated;
    }

    public CompanyAdministratorDto(CompanyAdministrator administrator) {
        this.id = administrator.getId();
        this.email = administrator.getEmail();
        this.password = administrator.getPassword();
        this.role = administrator.getRole();
        this.name = administrator.getName();
        this.surname = administrator.getSurname();
        this.city = administrator.getCity();
        this.country = administrator.getCountry();
        this.phoneNumber = administrator.getPhoneNumber();
        this.occupation = administrator.getOccupation();
        this.companyInfo = administrator.getCompanyInfo();
        this.activated = administrator.isActivated();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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
    /*
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    */
}
