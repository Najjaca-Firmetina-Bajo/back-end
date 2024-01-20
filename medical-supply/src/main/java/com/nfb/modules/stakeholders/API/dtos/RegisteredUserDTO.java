package com.nfb.modules.stakeholders.API.dtos;

import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegisteredUserDTO {

    @Schema(description = "User ID")
    private long id;

    @Schema(description = "Email")
    private String email;

    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY, description = "Password")
    private String password;

    @Schema(description = "User Role")
    private String role;

    @Schema(description = "User's Name")
    private String name;

    @Schema(description = "User's Surname")
    private String surname;

    @Schema(description = "User's City")
    private String city;

    @Schema(description = "User's Country")
    private String country;

    @Schema(description = "User's Phone Number")
    private String phoneNumber;

    @Schema(description = "User's Occupation")
    private String occupation;

    @Schema(description = "User's Company Information")
    private String companyInfo;

    @Schema(description = "Activation Status")
    private boolean activated;

    @Schema(description = "User's Penal points")
    private int penal;

    @Schema(description = "User's appointments")
    private List<Long> QRCodes;

    public int getPenal() {
        return penal;
    }

    public void setPenal(int penal) {
        this.penal = penal;
    }

    public RegisteredUserDTO(long id, String email, String password, String role,
                             String name, String surname, String city, String country,
                             String phoneNumber, String occupation, String companyInfo, boolean activated,int penal) {
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
        this.penal = penal;
        this.QRCodes = new ArrayList<>();
    }

    public RegisteredUserDTO(RegisteredUser user) {
        this.id = user.getId();
        this.email = user.getUsername();
        this.password = user.getPassword();
        this.role = "REGISTERED_USER";
        this.name = user.getName();
        this.surname = user.getSurname();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.phoneNumber = user.getPhoneNumber();
        this.occupation = user.getOccupation();
        this.companyInfo = user.getCompanyInfo();
        this.activated = user.isEnabled();
        this.penal = 0;
        this.QRCodes = user.getQRcodes().stream()
                .map(QRCode::getId)
                .collect(Collectors.toList());
    }


    public List<Long> getQRCodes() {
        return QRCodes;
    }

    public void setQRCodes(List<Long> QRCodes) {
        this.QRCodes = QRCodes;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public RegisteredUserDTO() {

    }
}
