package com.nfb.modules.stakeholders.core.domain.user;

import com.nfb.buildingblocks.core.domain.BaseEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String occupation;

    @Column(nullable = false)
    private String companyInfo;

    @Column(nullable = false)
    private boolean activated;

    // Constructors, getters, and setters

    public User() {
    }

    public User(String email, String password,UserRole role, String name, String surname, String city,
                String country, String phoneNumber, String occupation, String companyInfo) {
        super();
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.occupation = occupation;
        this.companyInfo = companyInfo;
        this.activated = false;
        validate();
    }

    // Getters and setters for other fields

    // Validation method
    private void validate() {
        // Your existing validation logic
        if (this.email == null || this.email.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Email");
        }
        if (this.password == null || this.password.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Password");
        }
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Name");
        }
        if (this.surname == null || this.surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Surname");
        }

    }

    public void activateAccount() {
        this.activated = true;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public boolean isActivated() {
        return activated;
    }
}
