package com.nfb.modules.companies.core.domain.equipment;

import com.nfb.buildingblocks.core.domain.Entity;

public class Equipment extends Entity {
    private String name;
    private String type;
    private String description;
    //lista kompanija


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
