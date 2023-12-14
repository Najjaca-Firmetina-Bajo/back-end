package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.core.usecases.WorkingCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workingCalendars")
public class WorkingCalendarController {

    @Autowired
    private WorkingCalendarService workingCalendarService;

    public WorkingCalendarController(WorkingCalendarService workingCalendarService) {
        this.workingCalendarService = workingCalendarService;
    }
}
