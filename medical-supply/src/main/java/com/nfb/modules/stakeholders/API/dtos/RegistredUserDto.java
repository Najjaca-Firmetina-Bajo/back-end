package com.nfb.modules.stakeholders.API.dtos;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.domain.user.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistredUserDto {
    @Schema(description = "RegistredUser ID")
    private long id;

    @Schema(description = "Email")
    private String email;

    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY, description = "Password")
    private String password;

    @Schema(description = "Role")
    private UserRole role;

    @Schema(description = "RegistredUser's Name")
    private String name;

    @Schema(description = "RegistredUser's Surname")
    private String surname;

    @Schema(description = "RegistredUser's City")
    private String city;

    @Schema(description = "RegistredUser's Country")
    private String country;

    @Schema(description = "RegistredUser's Phone Number")
    private String phoneNumber;

    @Schema(description = "RegistredUser's Occupation")
    private String occupation;

    @Schema(description = "RegistredUser's Company Information")
    private String companyInfo;

    @Schema(description = "Activation Status")
    private boolean activated;

    @Schema(description = "Appointments that made registered user")
    private List<Long> appointmentsIds;

    public RegistredUserDto(long id, String email, String password, UserRole role, String name, String surname, String city, String country, String phoneNumber, String occupation, String companyInfo, boolean activated) {
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
        this.appointmentsIds = new ArrayList<>();
    }

    public RegistredUserDto(RegisteredUser user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.phoneNumber = user.getPhoneNumber();
        this.occupation = user.getOccupation();
        this.companyInfo = user.getCompanyInfo();
        this.activated = user.isActivated();
        this.appointmentsIds = user.getAppointments().stream()
                .map(Appointment::getId)
                .collect(Collectors.toList());
    }

    public List<Long> getAppointmentsIds() {
        return appointmentsIds;
    }

    public void setAppointmentsIds(List<Long> appointmentsIds) {
        this.appointmentsIds = appointmentsIds;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
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
}
