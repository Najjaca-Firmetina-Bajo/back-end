package com.nfb.modules.companies.core.domain.equipment;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.domain.appointment.QREquipment;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.company.CompanyEquipment;
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
    @OneToMany(mappedBy = "equipment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CompanyEquipment> companyEquipmentList;
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<QREquipment> qrEquipments;


    public Equipment() {
    }

    public Equipment(String name, String type, String description, double price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.companyEquipmentList = new ArrayList<>();
        this.qrEquipments = new ArrayList<>();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<CompanyEquipment> getCompanyEquipmentList() {
        return companyEquipmentList;
    }

    public void setCompanyEquipmentList(List<CompanyEquipment> companyEquipmentList) {
        this.companyEquipmentList = companyEquipmentList;
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

    public List<QREquipment> getQrEquipments() {
        return qrEquipments;
    }

    public void setQrEquipments(List<QREquipment> qrEquipments) {
        this.qrEquipments = qrEquipments;
    }
}
