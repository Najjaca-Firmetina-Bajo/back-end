package com.nfb.modules.companies.core.domain.equipment;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipment")
public class Equipment extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String description;
    //lista kompanija


    public Equipment() {
    }

    public Equipment(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
        //prazna lista kompanija
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
