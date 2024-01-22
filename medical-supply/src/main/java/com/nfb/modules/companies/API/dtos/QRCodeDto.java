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
    private List<Long> reservedEquipmentIds;

    public QRCodeDto(QRCode qrCode) {
        this.id = qrCode.getId();
        this.code = qrCode.getCode();
        this.status = qrCode.getStatus();
        this.registeredUserId = (qrCode.getUser() != null) ? qrCode.getUser().getId() : null;
        this.appointmentId = (qrCode.getAppointment() != null) ? qrCode.getAppointment().getId() : null;
        this.reservedEquipmentIds = qrCode.getReservedEquipment().stream()
                .map(Equipment::getId)
                .collect(Collectors.toList());
    }


    public QRCodeDto(long id, String code, QRStatus status, Long registeredUserId, Long appointmentId, List<Long> reservedEquipmentIds) {
        this.id = id;
        this.code = code;
        this.status = status;
        this.registeredUserId = registeredUserId;
        this.appointmentId = appointmentId;
        this.reservedEquipmentIds = reservedEquipmentIds;
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

    public List<Long> getReservedEquipmentIds() {
        return reservedEquipmentIds;
    }
}
