package com.nfb.modules.stakeholders.core.domain.user;

import com.nfb.modules.companies.core.domain.company.Company;
import jakarta.persistence.*;

@Entity
@Table(name = "CompanyAdministrator")
public class CompanyAdministrator extends User{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    public CompanyAdministrator(Company company) {
        this.company = company;
    }

    public CompanyAdministrator(String email, String password,UserRole role, String name, String surname, String city,
                                String country, String phoneNumber, String occupation, String companyInfo, Company company) {
        super(email, password, UserRole.CompanyAdministrator, name, surname, city, country, phoneNumber, occupation, companyInfo);
        this.company = company;
    }

    public CompanyAdministrator() {

    }

    public Company getCompany() {
        return company;
    }

    private void setCompany(Company company) {
        this.company = company;
    }
}
