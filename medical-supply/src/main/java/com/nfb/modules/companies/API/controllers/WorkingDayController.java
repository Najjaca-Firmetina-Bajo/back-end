package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.core.usecases.WorkingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workingDays")
public class WorkingDayController {

    @Autowired
    private WorkingDayService workingDayService;

    public WorkingDayController(WorkingDayService workingDayService) {
        this.workingDayService = workingDayService;
    }
}
