package com.nfb.modules.stakeholders.core.domain.user;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.modules.companies.core.domain.company.Company;
import jakarta.persistence.*;

@Entity
@Table(name = "SystemAdministrator")
public class SystemAdministrator extends User {

    @Column(name = "power")
    private int power;
    public SystemAdministrator(String email, String password, Role role, String name, String surname, String city,
                                String country, String phoneNumber, String occupation, String companyInfo) {
        super(email,password,role,name,surname,city,country,phoneNumber,occupation,companyInfo,false);
        this.power = 0;
    }

    public SystemAdministrator() {
        // Default constructor required by JPA
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}

