package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.stakeholders.API.dtos.CompanyAdministratorDto;
import com.nfb.modules.stakeholders.API.dtos.RegistredUserDto;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.usecases.RegistredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/registeredUsers")
public class RegisteredUserController {

    @Autowired
    private RegistredUserService registredUserService;

    public RegisteredUserController(RegistredUserService registeredUserService) {
        this.registredUserService = registeredUserService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<RegistredUserDto>> getAll() {
        List<RegisteredUser> users = registredUserService.getAll();

        List<RegistredUserDto> dtos = new ArrayList<>();
        for (RegisteredUser ru : users) {
            dtos.add(new RegistredUserDto(ru));
        }

        return ResponseEntity.ok(dtos);
    }
}
