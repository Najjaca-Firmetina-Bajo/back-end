package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.stream.Collectors;

public class EquipmentDto {
    @Schema(description = "Equipment ID")
    private long id;
    @Schema(description = "Equipment name")
    private String name;
    @Schema(description = "Equipment type")
    private String type;
    @Schema(description = "Equipment description")
    private String description;
    @Schema(description = "Companies that have this equipment")
    private List<Long> companies;

    public EquipmentDto(long id, String name, String type, String description, List<Long> companies) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.companies = companies;
    }

    public EquipmentDto(Equipment equipment) {
        this.id = equipment.getId();
        this.name = equipment.getName();
        this.type = equipment.getType();
        this.description = equipment.getDescription();
        this.companies = equipment.getCompanies().stream()
                .map(Company::getId)
                .collect(Collectors.toList());
    }

    public List<Long> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Long> companies) {
        this.companies = companies;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
