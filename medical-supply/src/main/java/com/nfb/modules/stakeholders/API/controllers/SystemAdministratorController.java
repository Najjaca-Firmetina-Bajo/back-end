package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.companies.API.dtos.CompanyDto;
import com.nfb.modules.stakeholders.API.dtos.SystemAdministratorDto;
import com.nfb.modules.stakeholders.API.dtos.UserDTO;
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
    public void updatePassword(@PathVariable long adminId, @PathVariable String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        userService.updatePassword(encodedPassword, adminId);
        systemAdministratorService.updatePasswordChanged(adminId);
    }

    /*
    @PutMapping ("/update-password/{password}/{username}")
    public ResponseEntity<UserDTO> addAdministratorToCompany(@PathVariable String password, @PathVariable String username) {
        String encodedPassword = passwordEncoder.encode(password);
        UserDTO dto = new UserDTO (userService.updatePassword(encodedPassword, username));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    */
}
