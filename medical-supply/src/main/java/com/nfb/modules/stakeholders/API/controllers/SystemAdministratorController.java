package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.companies.API.dtos.CompanyDto;
import com.nfb.modules.stakeholders.API.dtos.SystemAdministratorDto;
import com.nfb.modules.stakeholders.core.domain.user.Role;
import com.nfb.modules.stakeholders.core.domain.user.SystemAdministrator;
import com.nfb.modules.stakeholders.core.usecases.RoleService;
import com.nfb.modules.stakeholders.core.usecases.SystemAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/systemAdministrators")
public class SystemAdministratorController {

    @Autowired
    private SystemAdministratorService systemAdministratorService;
    @Autowired
    private RoleService roleService;

    public SystemAdministratorController(SystemAdministratorService systemAdministratorService) {
        this.systemAdministratorService = systemAdministratorService;
    }

    @PutMapping("/update-password/{adminId}")
    public void updatePassword(@PathVariable long adminId) {
        systemAdministratorService.updatePasswordChanged(adminId);
    }
}
