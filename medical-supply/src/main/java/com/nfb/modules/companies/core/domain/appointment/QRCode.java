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

    @OneToMany(mappedBy = "qrCode", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QREquipment> reservedEquipment;

    //@Version
    //private Long version;

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

    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }

    public List<QREquipment> getReservedEquipment() {
        return reservedEquipment;
    }

    public void setReservedEquipment(List<QREquipment> reservedEquipment) {
        this.reservedEquipment = reservedEquipment;
    }
    /*
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    */
}
