package com.nfb.modules.companies.core.domain.company;
import com.nfb.buildingblocks.core.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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
    //lista opreme

    public Company() {
    }

    public Company(String name, String address, double averageRating) {
        this.name = name;
        this.address = address;
        this.averageRating = averageRating;
        validateAddressFormat();
    }

    private void validateAddressFormat() {
        String regex = "^[\\w\\s]+, \\d{5}, [\\w\\s]+, [\\w\\s]+$";
        if (!(address != null && Pattern.matches(regex, address.trim()))) {
            throw new IllegalArgumentException("Invalid address format");
        }
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
