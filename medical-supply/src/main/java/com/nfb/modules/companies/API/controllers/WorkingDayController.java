package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.WorkingDayDto;
import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import com.nfb.modules.companies.core.usecases.WorkingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/workingDays")
@CrossOrigin
public class WorkingDayController {

    @Autowired
    private WorkingDayService workingDayService;

    public WorkingDayController(WorkingDayService workingDayService) {
        this.workingDayService = workingDayService;
    }

    @GetMapping("/findBy/{workingCalendarId}")
    public ResponseEntity<List<WorkingDayDto>> findBy(@PathVariable long workingCalendarId) {

        List<WorkingDay> workingDays = workingDayService.getByWorkingCalendarId(workingCalendarId);

        List<WorkingDayDto> dtos = new ArrayList<>();
        for (WorkingDay wd : workingDays) {
            dtos.add(new WorkingDayDto(wd));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
