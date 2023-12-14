package com.nfb.modules.companies.core.domain.company;
import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.modules.companies.core.domain.calendar.WorkingCalendar;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private double averageRating;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "company_equipment",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private List<Equipment> availableEquipment;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CompanyAdministrator> administrators;
    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private WorkingCalendar workingCalendar;

    public Company() {
    }

    public Company(String name, String address, double averageRating, List<CompanyAdministrator> administrators) {
        this.name = name;
        this.address = address;
        this.averageRating = averageRating;
        this.availableEquipment = new ArrayList<>();
        this.administrators = administrators;
        this.workingCalendar = null;
    }

    public WorkingCalendar getWorkingCalendar() {
        return workingCalendar;
    }

    private void setWorkingCalendar(WorkingCalendar workingCalendar) {
        this.workingCalendar = workingCalendar;
    }

    public List<CompanyAdministrator> getAdministrators() {
        return administrators;
    }

    private void setAdministrators(List<CompanyAdministrator> administrators) {
        this.administrators = administrators;
    }

    public List<Equipment> getAvailableEquipment() {
        return availableEquipment;
    }

    private void setAvailableEquipment(List<Equipment> availableEquipment) {
        this.availableEquipment = availableEquipment;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setAverageRating(double averageRating) {
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
