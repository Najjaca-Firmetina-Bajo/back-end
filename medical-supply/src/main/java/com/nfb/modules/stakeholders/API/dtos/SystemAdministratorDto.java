package com.nfb.modules.stakeholders.API.dtos;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.domain.user.SystemAdministrator;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.stream.Collectors;

public class SystemAdministratorDto {
    @Schema(description = "SystemAdministrator ID")
    private long id;

    @Schema(description = "Email")
    private String email;

    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY, description = "Password")
    private String password;

    @Schema(description = "Role")
    private String role;

    @Schema(description = "SystemAdministrator's Name")
    private String name;

    @Schema(description = "SystemAdministrator's Surname")
    private String surname;

    @Schema(description = "SystemAdministrator's City")
    private String city;

    @Schema(description = "SystemAdministrator's Country")
    private String country;

    @Schema(description = "SystemAdministrator's Phone Number")
    private String phoneNumber;

    @Schema(description = "SystemAdministrator's Occupation")
    private String occupation;

    @Schema(description = "SystemAdministrator's Company Information")
    private String companyInfo;

    @Schema(description = "Activation Status")
    private boolean activated;

    @Schema(description = "SystemAdministrator power")
    private int power;

    public SystemAdministratorDto(long id, String email, String password, String role, String name, String surname, String city, String country, String phoneNumber, String occupation, String companyInfo, boolean activated, int power) {
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
        this.power = power;
    }

    public SystemAdministratorDto(SystemAdministrator user) {
        this.id = user.getId();
        this.email = user.getUsername();
        this.password = user.getPassword();
        this.role = "SYSTEM_ADMINISTRATOR";
        this.name = user.getName();
        this.surname = user.getSurname();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.phoneNumber = user.getPhoneNumber();
        this.occupation = user.getOccupation();
        this.companyInfo = user.getCompanyInfo();
        this.activated = user.isEnabled();
        this.power = user.getPower();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
