package com.nfb.modules.companies.core.domain.appointment;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity {
    @Column(nullable = false)
    private LocalDateTime pickUpDate;
    @Column(nullable = false)
    private int duration;
    @Column(nullable = false)
    private AppointmentType type;
    @Column(nullable = false)
    private boolean isDownloaded;
    @Column(nullable = false)
    private int reservationNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "appointment_equipment",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private List<Equipment> reservedEquipment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_administrator_id")
    private CompanyAdministrator companyAdministrator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "working_day_id")
    private WorkingDay workingDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registred_user_id")
    private RegisteredUser registeredUser;

    public Appointment() {
    }

    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }

    public WorkingDay getWorkingDay() {
        return workingDay;
    }

    private void setWorkingDay(WorkingDay workingDay) {
        this.workingDay = workingDay;
    }

    public LocalDateTime getPickUpDate() {
        return pickUpDate;
    }

    private void setPickUpDate(LocalDateTime pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public AppointmentType getType() {
        return type;
    }

    private void setType(AppointmentType type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    private void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    private void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    private void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public List<Equipment> getReservedEquipment() {
        return reservedEquipment;
    }

    private void setReservedEquipment(List<Equipment> reservedEquipment) {
        this.reservedEquipment = reservedEquipment;
    }

    public CompanyAdministrator getCompanyAdministrator() {
        return companyAdministrator;
    }

    private void setCompanyAdministrator(CompanyAdministrator companyAdministrator) {
        this.companyAdministrator = companyAdministrator;
    }
}
