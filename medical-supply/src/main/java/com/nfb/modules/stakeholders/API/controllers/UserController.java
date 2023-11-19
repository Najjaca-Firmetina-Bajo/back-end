package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.stakeholders.API.dtos.UserDTO;
import com.nfb.modules.stakeholders.core.domain.user.UserRole;
import com.nfb.modules.stakeholders.core.usecases.UserService;
import com.nfb.modules.stakeholders.core.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        User user = new User(
                userDTO.getEmail(),
                userDTO.getPassword(),
                UserRole.RegisteredUser,
                userDTO.getName(),
                userDTO.getSurname(),
                userDTO.getCity(),
                userDTO.getCountry(),
                userDTO.getPhoneNumber(),
                userDTO.getOccupation(),
                userDTO.getCompanyInfo()
        );

        user = userService.register(user);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED);
    }

    @PostMapping("/registerCompanyAdmin")
    public ResponseEntity<UserDTO> registerCompanyAdmin(@RequestBody UserDTO userDTO) {
        User user = new User(
                userDTO.getEmail(),
                userDTO.getPassword(),
                UserRole.CompanyAdministrator,
                userDTO.getName(),
                userDTO.getSurname(),
                userDTO.getCity(),
                userDTO.getCountry(),
                userDTO.getPhoneNumber(),
                userDTO.getOccupation(),
                userDTO.getCompanyInfo()
        );

        user = userService.register(user);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {

        List<User> users = userService.getAll();

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(new UserDTO(user));
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }


}
