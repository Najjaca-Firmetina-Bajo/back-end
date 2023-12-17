package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.AppointmentType;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    private List<Long> reservedEquipmentIds;
    @Schema(description = "Administrator of appointment")
    private Long companyAdministratorId;
    @Schema(description = "Working day of appointment")
    private Long workingDayId;
    @Schema(description = "RegistredUser who made appointment")
    private Long registredUserId;

    public AppointmentDto(long id, LocalDateTime pickUpDate, int duration, AppointmentType type, boolean isDownloaded, int reservationNumber) {
        this.id = id;
        this.pickUpDate = pickUpDate;
        this.duration = duration;
        this.type = type;
        this.isDownloaded = isDownloaded;
        this.reservationNumber = reservationNumber;
        this.reservedEquipmentIds = new ArrayList<>();
        this.companyAdministratorId = (long) -1;
        this.workingDayId = (long) -1;
        this.registredUserId = (long) -1;
    }

    public AppointmentDto(Appointment appointment) {
        this.id = appointment.getId();
        this.pickUpDate = appointment.getPickUpDate();
        this.duration = appointment.getDuration();
        this.type = appointment.getType();
        this.isDownloaded = appointment.isDownloaded();
        this.reservationNumber = appointment.getReservationNumber();
        this.reservedEquipmentIds = appointment.getReservedEquipment().stream()
                .map(Equipment::getId)
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
        if(appointment.getRegisteredUser() != null) {
            this.registredUserId = appointment.getRegisteredUser().getId();
        } else {
            this.registredUserId = (long) -1;
        }
    }

    public Long getRegistredUserId() {
        return registredUserId;
    }

    public void setRegistredUserId(Long registredUserId) {
        this.registredUserId = registredUserId;
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

    public List<Long> getReservedEquipmentIds() {
        return reservedEquipmentIds;
    }

    public void setReservedEquipmentIds(List<Long> reservedEquipmentIds) {
        this.reservedEquipmentIds = reservedEquipmentIds;
    }

    public Long getCompanyAdministratorId() {
        return companyAdministratorId;
    }

    public void setCompanyAdministratorId(Long companyAdministratorId) {
        this.companyAdministratorId = companyAdministratorId;
    }
}
