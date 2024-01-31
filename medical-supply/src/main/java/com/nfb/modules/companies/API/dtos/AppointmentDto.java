package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.AppointmentType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentDto {
    @Schema(description = "Appointment ID")
    private long id;
    @Schema(description = "Appointment pick up date")
    private LocalDateTime pickUpDate;
    @Schema(description = "Appointment duration")
    private int duration;
    @Schema(description = "Appointment type")
    private AppointmentType type;
    @Schema(description = "Appointment status")
    private boolean isDownloaded;
    @Schema(description = "Appointment reservation number")
    private int reservationNumber;
    @Schema(description = "Reserved equipment in appointment")
    private List<EquipmentQuantityDto> reservedEquipment;
    @Schema(description = "Administrator of appointment")
    private Long companyAdministratorId;
    @Schema(description = "Working day of appointment")
    private Long workingDayId;


    public AppointmentDto(long id, LocalDateTime pickUpDate, int duration, AppointmentType type, boolean isDownloaded, int reservationNumber) {
        this.id = id;
        this.pickUpDate = pickUpDate;
        this.duration = duration;
        this.type = type;
        this.isDownloaded = isDownloaded;
        this.reservationNumber = reservationNumber;
        this.reservedEquipment = new ArrayList<>();
        this.companyAdministratorId = (long) -1;
        this.workingDayId = (long) -1;
    }

    public AppointmentDto(Appointment appointment) {
        this.id = appointment.getId();
        this.pickUpDate = appointment.getPickUpDate();
        this.duration = appointment.getDuration();
        this.type = appointment.getType();
        this.isDownloaded = appointment.isDownloaded();
        this.reservationNumber = appointment.getReservationNumber();
        this.reservedEquipment = appointment.getQRCodes().stream()
                .flatMap(qrCode -> qrCode.getReservedEquipment().stream())
                .map(equipment -> new EquipmentQuantityDto(equipment.getEquipmentId(), equipment.getQuantity()))
                .collect(Collectors.toList());
        if(appointment.getCompanyAdministrator() != null) {
            this.companyAdministratorId = appointment.getCompanyAdministrator().getId();
        } else {
            this.companyAdministratorId = (long) -1;
        }
        if(appointment.getWorkingDay() != null) {
            this.workingDayId = appointment.getWorkingDay().getId();
        } else {
            this.workingDayId = (long) -1;
        }
    }


    public Long getWorkingDayId() {
        return workingDayId;
    }

    public void setWorkingDayId(Long workingDayId) {
        this.workingDayId = workingDayId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDateTime pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public List<EquipmentQuantityDto> getReservedEquipment() {
        return reservedEquipment;
    }

    public void setReservedEquipment(List<EquipmentQuantityDto> reservedEquipment) {
        this.reservedEquipment = reservedEquipment;
    }

    public Long getCompanyAdministratorId() {
        return companyAdministratorId;
    }

    public void setCompanyAdministratorId(Long companyAdministratorId) {
        this.companyAdministratorId = companyAdministratorId;
    }
}
