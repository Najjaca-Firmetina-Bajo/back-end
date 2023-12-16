package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.WorkingCalendarDto;
import com.nfb.modules.companies.API.dtos.WorkingDayDto;
import com.nfb.modules.companies.core.domain.calendar.WorkingCalendar;
import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import com.nfb.modules.companies.core.usecases.WorkingCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/workingCalendars")
@CrossOrigin
public class WorkingCalendarController {

    @Autowired
    private WorkingCalendarService workingCalendarService;

    public WorkingCalendarController(WorkingCalendarService workingCalendarService) {
        this.workingCalendarService = workingCalendarService;
    }

    @GetMapping("/findBy/{companyId}")
    public ResponseEntity<WorkingCalendarDto> findBy(@PathVariable long companyId) {
        WorkingCalendar workingCalendar = workingCalendarService.getBy(companyId);
        return new ResponseEntity<>(new WorkingCalendarDto(workingCalendar), HttpStatus.OK);
    }
}
