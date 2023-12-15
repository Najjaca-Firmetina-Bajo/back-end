package com.nfb.modules.stakeholders.core.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "RegisteredUser")
public class RegisteredUser extends User {

    @Column(name = "penal_points")
    private int penalPoints;

    public RegisteredUser(String email, String password, UserRole role, String name, String surname, String city,
                          String country, String phoneNumber, String occupation, String companyInfo, int penalPoints) {
        super(email, password, UserRole.RegisteredUser, name, surname, city, country, phoneNumber, occupation, companyInfo);
        this.penalPoints = penalPoints;
    }

    public RegisteredUser() {
        // Default constructor required by JPA
    }

    public int getPenalPoints() {
        return penalPoints;
    }

    public void setPenalPoints(int penalPoints) {
        this.penalPoints = penalPoints;
    }
}
