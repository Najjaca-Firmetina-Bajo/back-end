package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.usecases.CompanyService;
import com.nfb.modules.stakeholders.API.dtos.CompanyAdministratorDto;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.UserRole;
import com.nfb.modules.stakeholders.core.usecases.CompanyAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/companyAdministrators")
public class CompanyAdministratorController {

    @Autowired
    private CompanyAdministratorService companyAdministratorService;

    @Autowired
    private CompanyService companyService; //zbog razbijanja ciklicne zavisnosti CompanyAdministratorService - CompanyService

    public CompanyAdministratorController(CompanyAdministratorService companyAdministratorService, CompanyService companyService) {
        this.companyAdministratorService = companyAdministratorService;
        this.companyService = companyService;
    }

    @PostMapping("/register")
    public ResponseEntity<CompanyAdministratorDto> registerCompanyAdmin(@RequestBody CompanyAdministratorDto companyAdministratorDto) {

        CompanyAdministrator admin = new CompanyAdministrator(
                companyAdministratorDto.getEmail(),
                companyAdministratorDto.getPassword(),
                UserRole.CompanyAdministrator,
                companyAdministratorDto.getName(),
                companyAdministratorDto.getSurname(),
                companyAdministratorDto.getCity(),
                companyAdministratorDto.getCountry(),
                companyAdministratorDto.getPhoneNumber(),
                companyAdministratorDto.getOccupation(),
                companyAdministratorDto.getCompanyInfo(),
                companyService.findById(companyAdministratorDto.getCompanyId()).orElse(null)
        );
        admin = companyAdministratorService.register(admin);
        return new ResponseEntity<>(new CompanyAdministratorDto(admin), HttpStatus.CREATED);
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
        Company company = companyService.findById(companyId).orElse(null);
        return companyAdministratorService.setCompanyForAdministrator(adminId, company);
    }

}