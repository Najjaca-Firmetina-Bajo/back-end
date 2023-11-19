package com.nfb.modules.companies.core.domain.equipment;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.modules.companies.core.domain.company.Company;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "equipment")
public class Equipment extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String description;
    @ManyToMany(mappedBy = "availableEquipment",fetch = FetchType.EAGER)
    private List<Company> companies;


    public Equipment() {
    }

    public Equipment(String name, String type, String description, List<Company> companies) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    private void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setType(String type) {
        this.type = type;
    }

    private void setDescription(String description) {
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
