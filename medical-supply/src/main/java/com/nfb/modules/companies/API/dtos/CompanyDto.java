package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import io.swagger.v3.oas.annotations.media.Schema;

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

    public CompanyDto(long id, String name, String address, double averageRating, List<Long> availableEquipmentIds) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.averageRating = averageRating;
        this.availableEquipmentIds = availableEquipmentIds;
    }

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
        this.averageRating = company.getAverageRating();
        this.availableEquipmentIds = company.getAvailableEquipment().stream()
                .map(Equipment::getId)
                .collect(Collectors.toList());
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
