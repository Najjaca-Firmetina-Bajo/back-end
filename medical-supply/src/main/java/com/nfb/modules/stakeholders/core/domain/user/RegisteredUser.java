package com.nfb.modules.stakeholders.core.domain.user;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RegisteredUser")
public class RegisteredUser extends User {

    @Column(name = "penal_points")
    private int penalPoints;
    @OneToMany(mappedBy = "registeredUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<QRCode> QRcodes;

    public RegisteredUser(String email, String password, Role role, String name, String surname, String city,
                          String country, String phoneNumber, String occupation, String companyInfo, int penalPoints) {
        super(email,password,role,name,surname,city,country,phoneNumber,occupation,companyInfo,false);
        this.penalPoints = penalPoints;
        this.QRcodes = new ArrayList<>();
    }


    public RegisteredUser() {
        // Default constructor required by JPA
    }

    public List<QRCode> getQRcodes() {
        return QRcodes;
    }

    public void setQRcodes(List<QRCode> QRcodes) {
        this.QRcodes = QRcodes;
    }

    public int getPenalPoints() {
        return penalPoints;
    }

    public void setPenalPoints(int penalPoints) {
        this.penalPoints = penalPoints;
    }
}
