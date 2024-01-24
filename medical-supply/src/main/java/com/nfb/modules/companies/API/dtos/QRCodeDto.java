package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.domain.appointment.QRStatus;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.stream.Collectors;

public class QRCodeDto {
    @Schema(description = "QRCode ID")
    private long id;
    @Schema(description = "QRCode code")
    private String code;
    @Schema(description = "QRCode status")
    private QRStatus status;
    @Schema(description = "Registered user ID associated with QRCode")
    private Long registeredUserId;
    @Schema(description = "Appointment ID associated with QRCode")
    private Long appointmentId;
    @Schema(description = "Reserved equipment IDs associated with QRCode")
    private List<QRCodeEquipmentDto> reservedEquipment;

    public QRCodeDto(QRCode qrCode) {
        this.id = qrCode.getId();
        this.code = qrCode.getCode();
        this.status = qrCode.getStatus();
        this.registeredUserId = (qrCode.getUser() != null) ? qrCode.getUser().getId() : null;
        this.appointmentId = (qrCode.getAppointment() != null) ? qrCode.getAppointment().getId() : null;
        this.reservedEquipment = qrCode.getReservedEquipment().stream()
                .map(equipment -> new QRCodeEquipmentDto(equipment.getEquipmentId(), equipment.getQuantity()))
                .collect(Collectors.toList());

    }


    public QRCodeDto(long id, String code, QRStatus status, Long registeredUserId, Long appointmentId, List<QRCodeEquipmentDto> reservedEquipment) {
        this.id = id;
        this.code = code;
        this.status = status;
        this.registeredUserId = registeredUserId;
        this.appointmentId = appointmentId;
        this.reservedEquipment = reservedEquipment;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public QRStatus getStatus() {
        return status;
    }

    public Long getRegisteredUserId() {
        return registeredUserId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStatus(QRStatus status) {
        this.status = status;
    }

    public void setRegisteredUserId(Long registeredUserId) {
        this.registeredUserId = registeredUserId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public List<QRCodeEquipmentDto> getReservedEquipment() {
        return reservedEquipment;
    }

    public void setReservedEquipment(List<QRCodeEquipmentDto> reservedEquipment) {
        this.reservedEquipment = reservedEquipment;
    }
}
