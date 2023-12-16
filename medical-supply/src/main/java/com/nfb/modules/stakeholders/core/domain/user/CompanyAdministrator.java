package com.nfb.modules.stakeholders.core.domain.user;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.company.Company;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CompanyAdministrator")
public class CompanyAdministrator extends User{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "companyAdministrator", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public CompanyAdministrator(Company company) {
        this.company = company;
    }

    public CompanyAdministrator(String email, String password,Role role, String name, String surname, String city,
                                String country, String phoneNumber, String occupation, String companyInfo, Company company) {
        super(email,password,role,name,surname,city,country,phoneNumber,occupation,companyInfo,false);
        this.company = company;
        this.appointments = new ArrayList<>();
    }

    public CompanyAdministrator() {
        super();
    }

    public Company getCompany() {
        return company;
    }

    private void setCompany(Company company) {
        this.company = company;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    private void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
