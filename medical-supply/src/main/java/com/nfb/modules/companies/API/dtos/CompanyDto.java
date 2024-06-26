package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyDto {
    @Schema(description = "Company ID")
    private long id;
    @Schema(description = "Company name")
    private String name;
    @Schema(description = "Company address")
    private String address;
    @Schema(description = "Company description")
    private String description;
    @Schema(description = "Company rating")
    private double averageRating;
    @Schema(description = "Available equipment in company")
    private List<EquipmentQuantityDto> availableEquipment;
    @Schema(description = "Administrators of company")
    private List<Long> companyAdministraotrsIds;
    @Schema(description = "Working calendar of company")
    private Long workingCalendarId;


    public CompanyDto(long id, String name, String address, double averageRating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.averageRating = averageRating;
        this.availableEquipment = new ArrayList<>();
        this.companyAdministraotrsIds = new ArrayList<>();
        this.workingCalendarId = (long) -1;
    }

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
        this.averageRating = company.getAverageRating();
        this.description = company.getDescription();
        this.availableEquipment = company.getCompanyEquipmentList().stream()
                .map(ce -> new EquipmentQuantityDto(ce.getEquipmentId(), ce.getQuantity())) // Use EquipmentQuantityDto here
                .collect(Collectors.toList());
        this.companyAdministraotrsIds = company.getAdministrators().stream()
                .map(CompanyAdministrator::getId)
                .collect(Collectors.toList());
        if(company.getWorkingCalendar() != null)
            this.workingCalendarId = company.getWorkingCalendar().getId();
        else
            this.workingCalendarId = (long) -1;
    }

    public Long getWorkingCalendarId() {
        return workingCalendarId;
    }

    public void setWorkingCalendarId(Long workingCalendarId) {
        this.workingCalendarId = workingCalendarId;
    }

    public List<Long> getCompanyAdministraotrsIds() {
        return companyAdministraotrsIds;
    }

    public void setCompanyAdministraotrsIds(List<Long> companyAdministraotrsIds) {
        this.companyAdministraotrsIds = companyAdministraotrsIds;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<EquipmentQuantityDto> getAvailableEquipment() {
        return availableEquipment;
    }

    public void setAvailableEquipment(List<EquipmentQuantityDto> availableEquipment) {
        this.availableEquipment = availableEquipment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
