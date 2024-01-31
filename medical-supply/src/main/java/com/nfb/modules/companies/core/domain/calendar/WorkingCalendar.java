package com.nfb.modules.companies.core.domain.calendar;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.modules.companies.core.domain.company.Company;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "workingCalendars")
public class WorkingCalendar extends BaseEntity {

    @OneToMany(mappedBy = "workingCalendar", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<WorkingDay> workingDays;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    public WorkingCalendar() {
    }

    public List<WorkingDay> getWorkingDays() {
        return workingDays;
    }

    private void setWorkingDays(List<WorkingDay> workingDays) {
        this.workingDays = workingDays;
    }

    public Company getCompany() {
        return company;
    }

    private void setCompany(Company company) {
        this.company = company;
    }
}
