package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.companies.API.dtos.CompanyDto;
import com.nfb.modules.stakeholders.API.dtos.CompanyAdministratorDto;
import com.nfb.modules.stakeholders.API.dtos.SystemAdministratorDto;
import com.nfb.modules.stakeholders.API.dtos.UserDTO;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.Role;
import com.nfb.modules.stakeholders.core.domain.user.SystemAdministrator;
import com.nfb.modules.stakeholders.core.usecases.RoleService;
import com.nfb.modules.stakeholders.core.usecases.SystemAdministratorService;
import com.nfb.modules.stakeholders.core.usecases.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/systemAdministrators")
public class SystemAdministratorController {

    @Autowired
    private SystemAdministratorService systemAdministratorService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SystemAdministratorController(SystemAdministratorService systemAdministratorService, UserService userService) {
        this.systemAdministratorService = systemAdministratorService;
        this.userService = userService;
    }

    @PutMapping("/update-password/{adminId}/{newPassword}")
    public long updatePassword(@PathVariable long adminId, @PathVariable String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        userService.updatePassword(encodedPassword, adminId);
        systemAdministratorService.updatePasswordChanged(adminId);
        return adminId;
    }

    @GetMapping ("/get-all")
    public ResponseEntity<List<SystemAdministratorDto>> getAll() {
        List<SystemAdministrator> administrators = systemAdministratorService.getAll();

        List<SystemAdministratorDto> dtos = new ArrayList<>();
        for (SystemAdministrator sa : administrators) {
            dtos.add(new SystemAdministratorDto(sa));
        }

        return ResponseEntity.ok(dtos);
    }

}
