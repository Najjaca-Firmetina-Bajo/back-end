package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.calendar.WorkingCalendar;
import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkingCalendarDto {
    @Schema(description = "Working calendar company")
    private Long companyId;
    @Schema(description = "Working days of working calendar")
    private List<Long> workingDaysIds;

    public WorkingCalendarDto() {
        this.companyId = (long) -1;
        this.workingDaysIds = new ArrayList<>();
    }

    public WorkingCalendarDto(WorkingCalendar workingCalendar) {
        this.companyId = workingCalendar.getCompany().getId();
        this.workingDaysIds = workingCalendar.getWorkingDays().stream()
                .map(WorkingDay::getId)
                .collect(Collectors.toList());
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
