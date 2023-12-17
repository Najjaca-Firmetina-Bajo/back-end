package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.stakeholders.API.dtos.RegisteredUserDto;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.usecases.RegisteredUserService;
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
    private RegisteredUserService registeredUserService;

    public RegisteredUserController(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<RegisteredUserDto>> getAll() {
        List<RegisteredUser> users = registeredUserService.getAll();

        List<RegisteredUserDto> dtos = new ArrayList<>();
        for (RegisteredUser ru : users) {
            dtos.add(new RegisteredUserDto(ru));
        }

        return ResponseEntity.ok(dtos);
    }
}
