package com.nfb.modules.stakeholders.core.domain.user;

import jakarta.persistence.*;

@Entity
@Table(name = "patike")
public class Patike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "model", nullable = false)
    private String model;
    private int size;

    // Constructors, getters, and setters

    public Patike() {
    }

    public Patike(String brand, String model, int size) {
        this.brand = brand;
        this.model = model;
        this.size = size;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // toString() for debugging

    @Override
    public String toString() {
        return "Patike{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", size=" + size +
                '}';
    }
}
