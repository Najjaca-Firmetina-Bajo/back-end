package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.core.domain.appointment.ExtraordinaryAppointment;
import com.nfb.modules.companies.core.usecases.CompanyService;
import com.nfb.modules.companies.core.usecases.ExtraordinaryAppointmentService;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/extraordinaryAppointments")
@CrossOrigin
public class ExtraordinaryAppointmentController {

    @Autowired
    private ExtraordinaryAppointmentService extraordinaryAppointmentService;
    @Autowired
    private CompanyService companyService;

    public ExtraordinaryAppointmentController(ExtraordinaryAppointmentService extraordinaryAppointmentService, CompanyService companyService){
        this.extraordinaryAppointmentService = extraordinaryAppointmentService;
        this.companyService = companyService;
    }

    @GetMapping("/createIfCompanyIsWorking")
    public ResponseEntity<String> createIfCompanyIsWorking(@RequestParam Date date, @RequestParam long companyId){

    }
}
