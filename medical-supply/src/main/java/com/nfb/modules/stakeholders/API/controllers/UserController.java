package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.stakeholders.API.dtos.UserDTO;
import com.nfb.modules.stakeholders.core.domain.user.Role;
import com.nfb.modules.stakeholders.core.usecases.UserService;
import com.nfb.modules.stakeholders.core.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {

        List<User> users = userService.getAll();

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(new UserDTO(user));
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    //401 - ovaj PutMapping ne radi
    @PutMapping ("/update-password/{password}/{username}")
    public ResponseEntity<UserDTO> addAdministratorToCompany(@PathVariable String password, @PathVariable String username) {
        UserDTO dto = new UserDTO (userService.updatePassword(password, username));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/is-system-administrator/{username}")
    public boolean isSystemAdministrator(@PathVariable String username) {
        User user = userService.findByUsername(username);
        for(Role r: user.getRoles()) {
            if(r.getName().equals("SYSTEM_ADMINISTRATOR")) return true;
        }
        return false;
    }
}
