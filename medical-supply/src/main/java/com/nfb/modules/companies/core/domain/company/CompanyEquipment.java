package com.nfb.modules.companies.core.domain.company;


import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import jakarta.persistence.*;

@Entity
@Table(name = "company_equipment",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"company_id", "equipment_id"})})
public class CompanyEquipment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @Column(nullable = false)
    private int quantity;

    public CompanyEquipment() {
    }

    public CompanyEquipment(Company company, Equipment equipment, int quantity) {
        this.company = company;
        this.equipment = equipment;
        this.quantity = quantity;
    }

    public Company getCompany() {
        return company;
    }
    public Long getCompanyId() {
        return company.getId();
    }

    public Long getEquipmentId() {
        return equipment.getId();
    }
    public void setCompany(Company company) {
        this.company = company;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

