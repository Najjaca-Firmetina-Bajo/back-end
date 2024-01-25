package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.appointment.QREquipment;
import com.nfb.modules.companies.core.domain.appointment.QRStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class QREquipmentDto {

    @Schema(description = "QRCode ID")
    private long qrCodeId;
    @Schema(description = "Equipment ID")
    private long equipmentId;
    @Schema(description = "Reservation quantity")
    private int quantity;

    public QREquipmentDto(QREquipment qrEquipment) {
        this.qrCodeId = qrEquipment.getQrCodeID();
        this.equipmentId = qrEquipment.getEquipmentId();
        this.quantity = qrEquipment.getQuantity();
    }

    public QREquipmentDto(long qrCode, long equipmentId, int quantity) {
        this.qrCodeId = qrCode;
        this.equipmentId = equipmentId;
        this.quantity = quantity;
    }

    public long getQrCode() {
        return qrCodeId;
    }

    public void setQrCode(long qrCode) {
        this.qrCodeId = qrCode;
    }

    public long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
