package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.stakeholders.API.dtos.SystemAdministratorDto;
import com.nfb.modules.stakeholders.core.domain.user.SystemAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.UserRole;
import com.nfb.modules.stakeholders.core.usecases.SystemAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/systemAdministrators")
public class SystemAdministratorController {

    @Autowired
    private SystemAdministratorService systemAdministratorService;

    public SystemAdministratorController(SystemAdministratorService systemAdministratorService) {
        this.systemAdministratorService = systemAdministratorService;
    }

    @PostMapping("/register")
    public ResponseEntity<SystemAdministratorDto> registerSystemAdmin(@RequestBody SystemAdministratorDto systemAdministratorDto) {

        SystemAdministrator admin = new SystemAdministrator(
                systemAdministratorDto.getEmail(),
                systemAdministratorDto.getPassword(),
                UserRole.SystemAdministrator,
                systemAdministratorDto.getName(),
                systemAdministratorDto.getSurname(),
                systemAdministratorDto.getCity(),
                systemAdministratorDto.getCountry(),
                systemAdministratorDto.getPhoneNumber(),
                systemAdministratorDto.getOccupation(),
                systemAdministratorDto.getCompanyInfo()
        );
        admin = systemAdministratorService.register(admin);
        return new ResponseEntity<>(new SystemAdministratorDto(admin), HttpStatus.CREATED);
    }
}
