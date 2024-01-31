package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.calendar.WorkingCalendar;
import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkingCalendarDto {
    @Schema(description = "Working calendar ID")
    private long id;
    @Schema(description = "Working calendar company")
    private Long companyId;
    @Schema(description = "Working days of working calendar")
    private List<Long> workingDaysIds;

    public WorkingCalendarDto(long id) {
        this.id = id;
        this.companyId = (long) -1;
        this.workingDaysIds = new ArrayList<>();
    }

    public WorkingCalendarDto(WorkingCalendar workingCalendar) {
        this.id = workingCalendar.getId();
        if(workingCalendar.getCompany() != null){
            this.companyId = workingCalendar.getCompany().getId();
        }
        else {
            this.companyId = (long) -1;
        }
        this.workingDaysIds = workingCalendar.getWorkingDays().stream()
                .map(WorkingDay::getId)
                .collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<Long> getWorkingDaysIds() {
        return workingDaysIds;
    }

    public void setWorkingDaysIds(List<Long> workingDaysIds) {
        this.workingDaysIds = workingDaysIds;
    }
}
