package com.nfb.modules.companies.core.domain.appointment;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "extraordinary_appointments")
public class ExtraordinaryAppointment extends BaseEntity {
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

    @Column(nullable = false)
    private long winnerId;
    @OneToMany(mappedBy = "extraordinaryAppointment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<QRCode> QRCodes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_administrator_id")
    private CompanyAdministrator companyAdministrator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "working_day_id")
    private WorkingDay workingDay;

    @Column
    private LocalDateTime downloadedAt;

    @Version
    private Long version;


    public ExtraordinaryAppointment() {
    }

    public ExtraordinaryAppointment(LocalDateTime pickUpDate, int duration, AppointmentType type, boolean isDownloaded, int reservationNumber, WorkingDay workingDay, long winnerId) {
        this.pickUpDate = pickUpDate;
        this.duration = duration;
        this.type = type;
        this.isDownloaded = isDownloaded;
        this.reservationNumber = reservationNumber;
        this.workingDay = workingDay;
        this.winnerId = winnerId;
    }

    //duration, is_downloaded, reservation_number, type, company_administrator_id, id, pick_up_date, working_day_id,winner_id

    public long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(long winnerId) {
        this.winnerId = winnerId;
    }

    public WorkingDay getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(WorkingDay workingDay) {
        this.workingDay = workingDay;
    }

    public LocalDateTime getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDateTime pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public CompanyAdministrator getCompanyAdministrator() {
        return companyAdministrator;
    }

    public void setCompanyAdministrator(CompanyAdministrator companyAdministrator) {
        this.companyAdministrator = companyAdministrator;
    }

    public List<QRCode> getQRCodes() {
        // Assuming QRCode has a getStatus() method that returns the status
        return QRCodes.stream()
                .filter(qrCode -> !QRStatus.CANCELED.equals(qrCode.getStatus()))
                .collect(Collectors.toList());
    }

    public List<QRCode> getCanceledQRCodes() {
        // Assuming QRCode has a getStatus() method that returns the status
        return QRCodes.stream()
                .filter(qrCode -> QRStatus.CANCELED.equals(qrCode.getStatus()))
                .collect(Collectors.toList());
    }

    public void setQRCodes(List<QRCode> QRCodes) {
        this.QRCodes = QRCodes;
    }


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public LocalDateTime getDownloadedAt() {
        return downloadedAt;
    }

    public void setDownloadedAt(LocalDateTime downloadedAt) {
        this.downloadedAt = downloadedAt;
    }
}
