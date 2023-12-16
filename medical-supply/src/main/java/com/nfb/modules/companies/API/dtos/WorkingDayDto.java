package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class WorkingDayDto {
    @Schema(description = "Working day ID")
    private long id;
    @Schema(description = "Working day date")
    private Date date;
    @Schema(description = "Working day appointments")
    private List<Long> appointmentsIds;
    @Schema(description = "Working calendar of working day")
    private Long workingCalendarId;

    public WorkingDayDto(long id, Date date) {
        this.id = id;
        this.date = date;
        this.appointmentsIds = new ArrayList<>();
        this.workingCalendarId = (long) -1;
    }

    public WorkingDayDto(WorkingDay workingDay) {
        this.id = workingDay.getId();
        this.date = workingDay.getDate();
        this.appointmentsIds = workingDay.getAppointments().stream()
                .map(Appointment::getId)
                .collect(Collectors.toList());
        this.workingCalendarId = workingDay.getWorkingCalendar().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getWorkingCalendarId() {
        return workingCalendarId;
    }

    public void setWorkingCalendarId(Long workingCalendarId) {
        this.workingCalendarId = workingCalendarId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Long> getAppointmentsIds() {
        return appointmentsIds;
    }

    public void setAppointmentsIds(List<Long> appointmentsIds) {
        this.appointmentsIds = appointmentsIds;
    }
}
