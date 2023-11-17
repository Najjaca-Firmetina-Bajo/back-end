package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.stakeholders.API.dtos.UserDTO;
import com.nfb.modules.stakeholders.API.services.UserService;
import com.nfb.modules.stakeholders.core.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/medical-supply")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/all")

    public ResponseEntity<List<UserDTO>> getUsers() {

        //List<User> users = userService.findAll();

        /*
        // convert courses to DTOs
        List<UserDTO> userDTO = new ArrayList<>();
        for (User u : users) {
            userDTO.add(new UserDTO());
        }
        */


        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
