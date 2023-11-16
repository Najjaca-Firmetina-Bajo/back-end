package com.nfb.modules.companies.core.domain.company;
import com.nfb.buildingblocks.core.domain.Entity;

import java.util.regex.Pattern;

public class Company extends Entity {
    private String name;
    private String address;
    private double averageRating;
    //lista opreme

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
