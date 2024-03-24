package com.nfb.modules.companies.core.domain.calendar;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "workingDays")
public class WorkingDay extends BaseEntity {

    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private Date endDate;
    @OneToMany(mappedBy = "workingDay", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "working_calendar_id")
    private WorkingCalendar workingCalendar;

    public WorkingDay() {
    }

    public Date getDate() {
        return date;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    public Date getEndDate() {
        return endDate;
    }

    private void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    private void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public WorkingCalendar getWorkingCalendar() {
        return workingCalendar;
    }

    private void setWorkingCalendar(WorkingCalendar workingCalendar) {
        this.workingCalendar = workingCalendar;
    }
}
