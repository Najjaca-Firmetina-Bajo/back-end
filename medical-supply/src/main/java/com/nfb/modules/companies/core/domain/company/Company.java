package com.nfb.modules.companies.core.domain.company;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.modules.companies.core.domain.calendar.WorkingCalendar;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private double averageRating;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CompanyEquipment> companyEquipmentList;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CompanyAdministrator> administrators;
    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private WorkingCalendar workingCalendar;

    public Company(String name, String address, double averageRating, List<CompanyAdministrator> administrators) {
        this.name = name;
        this.address = address;
        this.averageRating = averageRating;
        this.companyEquipmentList = new ArrayList<>();
        this.administrators = administrators;
        this.workingCalendar = null;
    }

    /*
    public Company() {
    }

    public Company(String name, String address, double averageRating, List<CompanyAdministrator> administrators) {
        this.name = name;
        this.address = address;
        this.averageRating = averageRating;
        this.companyEquipmentList = new ArrayList<>();
        this.administrators = administrators;
        this.workingCalendar = null;
    }

    public WorkingCalendar getWorkingCalendar() {
        return workingCalendar;
    }

    public void setWorkingCalendar(WorkingCalendar workingCalendar) {
        this.workingCalendar = workingCalendar;
    }

    public List<CompanyAdministrator> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<CompanyAdministrator> administrators) {
        this.administrators = administrators;
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
     */
}
