package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.stakeholders.API.dtos.RegisteredUserDTO;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.usecases.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<RegisteredUserDTO>> getAll() {
        List<RegisteredUser> users = registeredUserService.getAll();

        List<RegisteredUserDTO> dtos = new ArrayList<>();
        for (RegisteredUser ru : users) {
            dtos.add(new RegisteredUserDTO(ru));
        }

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/give-penal-points/{userId}")
    public int givePenalPoints(@PathVariable long userId) {
        return registeredUserService.givePenalPoints(userId);
    }
}
