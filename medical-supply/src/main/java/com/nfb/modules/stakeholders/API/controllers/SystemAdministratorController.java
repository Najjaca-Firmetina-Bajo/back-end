package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.stakeholders.core.usecases.SystemAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/systemAdministrators")
@CrossOrigin
public class SystemAdministratorController {

    @Autowired
    private SystemAdministratorService systemAdministratorService;

    public SystemAdministratorController(SystemAdministratorService systemAdministratorService) {
        this.systemAdministratorService = systemAdministratorService;
    }

}
