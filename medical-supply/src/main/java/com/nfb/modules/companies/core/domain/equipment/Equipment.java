package com.nfb.modules.companies.core.domain.equipment;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.domain.company.Company;
import jakarta.persistence.*;

import java.util.ArrayList;
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
    @Column(nullable = false)
    private double price;
    @ManyToMany(mappedBy = "availableEquipment")
    private List<Company> companies;
    @ManyToMany(mappedBy = "reservedEquipment")
    private List<QRCode> QRCodes;


    public Equipment() {
    }

    public Equipment(String name, String type, String description, double price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.companies = new ArrayList<>();
        this.QRCodes = new ArrayList<>();
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        this.price = price;
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

    public List<QRCode> getQRCodes() {
        return QRCodes;
    }

    public void setQRCodes(List<QRCode> QRCodes) {
        this.QRCodes = QRCodes;
    }
}
