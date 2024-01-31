package com.nfb.modules.companies.core.domain.appointment;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.modules.companies.core.domain.equipment.Equipment;

import jakarta.persistence.*;

@Entity
@Table(name = "qrcode_equipment",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"qrcode_id", "equipment_id"})})
public class QREquipment extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "qrcode_id")
    private QRCode qrCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @Column(nullable = false)
    private int quantity;

    public QREquipment() {
    }

    public QREquipment(QRCode qrCode, Equipment equipment, int quantity) {
        this.qrCode = qrCode;
        this.equipment = equipment;
        this.quantity = quantity;
    }

    public QRCode getQrCode() {
        return qrCode;
    }

    public void setQrCode(QRCode qrCode) {
        this.qrCode = qrCode;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Long getEquipmentId() {
        return equipment.getId();
    }
    public Long getQrCodeID() {
        return qrCode.getId();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
