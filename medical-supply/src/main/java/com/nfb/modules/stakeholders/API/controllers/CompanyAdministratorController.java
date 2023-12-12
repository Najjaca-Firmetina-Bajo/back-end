package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.stakeholders.API.dtos.CompanyAdministratorDto;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.UserRole;
import com.nfb.modules.stakeholders.core.usecases.CompanyAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companyAdministrators")
public class CompanyAdministratorController {

    @Autowired
    private CompanyAdministratorService companyAdministratorService;

    public CompanyAdministratorController(CompanyAdministratorService companyAdministratorService) {
        this.companyAdministratorService = companyAdministratorService;
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
                companyAdministratorDto.getCompanyInfo());
        admin = companyAdministratorService.register(admin);
        return new ResponseEntity<>(new CompanyAdministratorDto(admin), HttpStatus.CREATED);
    }
}
