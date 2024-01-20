package com.nfb.modules.companies.core.domain.appointment;
import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "qrcodes")
public class QRCode extends BaseEntity {
    @Column(nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QRStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registered_user_id")
    private RegisteredUser registeredUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "qrcode_equipment",
            joinColumns = @JoinColumn(name = "qrcode_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private List<Equipment> reservedEquipment;

    public QRCode() {
    }

    public QRCode(String code, QRStatus status, RegisteredUser user, Appointment appointment) {
        this.code = code;
        this.status = status;
        this.registeredUser = user;
        this.appointment = appointment;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public QRStatus getStatus() {
        return status;
    }

    public void setStatus(QRStatus status) {
        this.status = status;
    }

    public RegisteredUser getUser() {
        return registeredUser;
    }

    public void setUser(RegisteredUser user) {
        this.registeredUser = user;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public List<Equipment> getReservedEquipment() {
        return reservedEquipment;
    }

    public void setReservedEquipment(List<Equipment> reservedEquipment) {
        this.reservedEquipment = reservedEquipment;
    }
}
