package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;

import java.util.List;

public class CompanyUpdateDto {
    private String name;
    private String address;
    private String description;
    private double avgRating;
    //private List<Appointment> deliveryAppointments
    private List<CompanyAdministrator> administrators;

    public CompanyUpdateDto() {
    }

    public CompanyUpdateDto(String name, String address, String description, double avgRating, List<CompanyAdministrator> administrators) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.avgRating = avgRating;
        this.administrators = administrators;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public List<CompanyAdministrator> getAdministrators() {
        return administrators;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public void setAdministrators(List<CompanyAdministrator> administrators) {
        this.administrators = administrators;
    }
}
