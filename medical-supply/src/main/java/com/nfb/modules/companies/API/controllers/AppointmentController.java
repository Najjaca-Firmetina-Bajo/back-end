package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.AppointmentDto;
import com.nfb.modules.companies.API.dtos.CompanyDto;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.calendar.WorkingDay;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.usecases.AppointmentService;
import com.nfb.modules.companies.core.usecases.CompanyService;
import com.nfb.modules.companies.core.usecases.WorkingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private WorkingDayService workingDayService;

    public AppointmentController(AppointmentService appointmentService,WorkingDayService workingDayService) {
        this.appointmentService = appointmentService;
        this.workingDayService = workingDayService;
    }

    @GetMapping("/findBy/{workingDayId}")
    public ResponseEntity<List<AppointmentDto>> findBy(@PathVariable long workingDayId) {

        List<Appointment> appointments = appointmentService.getBy(workingDayId);

        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment a : appointments) {
            dtos.add(new AppointmentDto(a));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AppointmentDto>> findBy() {

        List<Appointment> appointments = appointmentService.getAll();

        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment a : appointments) {
            dtos.add(new AppointmentDto(a));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/get-all-calendar/{id}")
    public ResponseEntity<List<AppointmentDto>> findByCompany(@PathVariable long id) {

        List<Appointment> appointments = appointmentService.getAll();
        List<WorkingDay> days = workingDayService.getBy(id);

        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment a : appointments) {
            if(days.stream().anyMatch(item -> a.getWorkingDay().getId() ==item.getId() ) ){
                dtos.add(new AppointmentDto(a));
            }

        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
