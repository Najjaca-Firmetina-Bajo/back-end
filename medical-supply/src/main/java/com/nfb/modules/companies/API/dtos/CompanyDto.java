package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.company.Company;
import io.swagger.v3.oas.annotations.media.Schema;

public class CompanyDto {
    @Schema(description = "Company ID")
    private long id;
    @Schema(description = "Company name")
    private String name;
    @Schema(description = "Company address")
    private String address;
    @Schema(description = "Company rating")
    private double averageRating;

    public CompanyDto(long id, String name, String address, double averageRating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.averageRating = averageRating;
    }

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
        this.averageRating = company.getAverageRating();
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getAverageRating() {
        return averageRating;
    }
}
