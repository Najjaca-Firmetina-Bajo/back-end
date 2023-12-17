package com.nfb.modules.stakeholders.API.dtos;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyAdministratorDto {
    @Schema(description = "CompanyAdministrator ID")
    private long id;

    @Schema(description = "Email")
    private String email;

    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY, description = "Password")
    private String password;

    @Schema(description = "Role")
    private String role;

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

    @Schema(description = "Company")
    private long companyId;

    @Schema(description = "Appointments of company administrators")
    private List<Long> appointmentsIds;

    public CompanyAdministratorDto(long id, String email, String password, String role, String name, String surname, String city, String country, String phoneNumber, String occupation, String companyInfo, boolean activated, long companyId) {
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
        this.companyId = companyId;
        this.appointmentsIds = new ArrayList<>();
    }

    public CompanyAdministratorDto(CompanyAdministrator administrator) {
        this.id = administrator.getId();
        this.email = administrator.getUsername();
        this.password = administrator.getPassword();
        this.role = "COMPANY_ADMINISTRATOR";
        this.name = administrator.getName();
        this.surname = administrator.getSurname();
        this.city = administrator.getCity();
        this.country = administrator.getCountry();
        this.phoneNumber = administrator.getPhoneNumber();
        this.occupation = administrator.getOccupation();
        this.companyInfo = administrator.getCompanyInfo();
        this.activated = administrator.isEnabled();
        if(administrator.getCompany() != null)
            this.companyId = administrator.getCompany().getId();
        else
            this.companyId = -1;
        this.appointmentsIds = administrator.getAppointments().stream()
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

}
