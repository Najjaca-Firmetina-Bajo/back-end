package com.nfb.modules.stakeholders.core.domain.user;

import com.nfb.modules.companies.core.domain.company.Company;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "companyAdministrators")
public class CompanyAdministrator extends User{
    @ManyToOne
    private Company company;

    public CompanyAdministrator(Company company) {
        this.company = company;
    }

    public CompanyAdministrator(String email, String password,UserRole role, String name, String surname, String city,
                                String country, String phoneNumber, String occupation, String companyInfo) {
        super(email, password, role, name, surname, city, country, phoneNumber, occupation, companyInfo);
        this.company = null;
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
