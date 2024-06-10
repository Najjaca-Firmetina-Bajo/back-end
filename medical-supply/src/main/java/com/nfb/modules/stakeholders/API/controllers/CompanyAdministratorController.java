package com.nfb.modules.stakeholders.API.controllers;
import com.nfb.modules.companies.API.dtos.*;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.usecases.CompanyAppointmentService;
import com.nfb.modules.companies.core.usecases.CompanyService;
import com.nfb.modules.stakeholders.API.dtos.AdminInfoDto;
import com.nfb.modules.stakeholders.API.dtos.CompanyAdministratorDto;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.usecases.CompanyAdministratorService;
import com.nfb.modules.stakeholders.core.usecases.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/companyAdministrators")
@CrossOrigin
public class CompanyAdministratorController {

    @Autowired
    private CompanyAdministratorService companyAdministratorService;
    @Autowired
    CompanyAppointmentService companyAppointmentService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private CompanyService companyService; //zbog razbijanja ciklicne zavisnosti CompanyAdministratorService - CompanyService

    public CompanyAdministratorController(CompanyAdministratorService companyAdministratorService, CompanyService companyService, RoleService roleService,
                                          CompanyAppointmentService companyAppointmentService) {
        this.companyAdministratorService = companyAdministratorService;
        this.companyService = companyService;
        this.roleService = roleService;
        this.companyAppointmentService = companyAppointmentService;
    }

    @GetMapping ("/get-all")
    public ResponseEntity<List<CompanyAdministratorDto>> getAll() {
        List<CompanyAdministrator> administrators = companyAdministratorService.getAll();

        List<CompanyAdministratorDto> dtos = new ArrayList<>();
        for (CompanyAdministrator ca : administrators) {
            dtos.add(new CompanyAdministratorDto(ca));
        }

        return ResponseEntity.ok(dtos);
    }

    @PutMapping ("/set-company/{adminId}/{companyId}")
    public int setCompanyForAdministrator(@PathVariable long adminId, @PathVariable long companyId) {
        //Company company = companyService.findById(companyId).orElse(null);
        return companyAdministratorService.setCompanyForAdministrator(adminId, companyId);
    }

    @GetMapping("/get-company/{adminId}")
    public ResponseEntity<CompanyInfoDto> getCompany(@PathVariable long adminId) {
        CompanyInfoDto companyInfoDto = companyAppointmentService.getById(adminId);

        return ResponseEntity.ok(companyInfoDto);
    }

    @PutMapping("/update-company-info")
    public ResponseEntity<Void> updateInfo(@RequestBody EditCompanyDto companyDto) {
        companyService.updateInfo(companyDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("get-working-calendar/{companyId}")
    public ResponseEntity<WorkingCalendarInfoDto> getWorkingCalendar(@PathVariable long companyId) {
        WorkingCalendarInfoDto workingCalendarInfoDto = companyAppointmentService.getWorkingCalendar(companyId);
        return ResponseEntity.ok(workingCalendarInfoDto);
    }

    @PostMapping("create-appointment")
    public ResponseEntity<Void> createAppointment(@RequestBody CreateAppointmentDto createAppointmentDto) {
        companyAppointmentService.create(createAppointmentDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-profile-info")
    public ResponseEntity<Void> updateProfile(@RequestBody AdminInfoDto adminInfoDto) {
        companyAdministratorService.update(adminInfoDto);
        return ResponseEntity.ok().build();
    }



}