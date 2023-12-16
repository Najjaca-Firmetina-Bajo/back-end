package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
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
    @Schema(description = "Company rating")
    private double averageRating;
    @Schema(description = "Available equipment in company")
    private List<Long> availableEquipmentIds;
    @Schema(description = "Administrators of company")
    private List<Long> companyAdministraotrsIds;
    @Schema(description = "Working calendar of company")
    private Long workingCalendarId;


    public CompanyDto(long id, String name, String address, double averageRating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.averageRating = averageRating;
        this.availableEquipmentIds = new ArrayList<>();
        this.companyAdministraotrsIds = new ArrayList<>();
        this.workingCalendarId = (long) -1;
    }

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
        this.averageRating = company.getAverageRating();
        this.availableEquipmentIds = company.getAvailableEquipment().stream()
                .map(Equipment::getId)
                .collect(Collectors.toList());
        this.companyAdministraotrsIds = company.getAdministrators().stream()
                .map(CompanyAdministrator::getId)
                .collect(Collectors.toList());
        this.workingCalendarId = company.getWorkingCalendar().getId();
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

    public List<Long> getAvailableEquipmentIds() {
        return availableEquipmentIds;
    }

    public void setAvailableEquipmentIds(List<Long> availableEquipmentIds) {
        this.availableEquipmentIds = availableEquipmentIds;
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
}
