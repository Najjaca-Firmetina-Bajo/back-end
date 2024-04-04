package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.stakeholders.API.dtos.RegisteredUserDTO;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.domain.user.Role;
import com.nfb.modules.stakeholders.core.usecases.RegisteredUserService;
import com.nfb.modules.stakeholders.core.usecases.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/registeredUsers")
public class RegisteredUserController {

    @Autowired
    private RegisteredUserService registeredUserService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @GetMapping("/get_registered_user/{id}")
    public ResponseEntity<RegisteredUserDTO> getRegisteredUser(@PathVariable long id){
        RegisteredUserDTO registeredUserDTO = new RegisteredUserDTO(registeredUserService.getRegisteredUser(id));
        return ResponseEntity.ok(registeredUserDTO);
    }

    @PutMapping("/update-registered-user")
    public ResponseEntity<RegisteredUserDTO> updateRegisteredUser(@RequestBody RegisteredUserDTO registeredUserDTO){

        List<Role> roles = roleService.findByName("REGISTERED_USER");
        RegisteredUser userForUpdating = registeredUserService.getRegisteredUser(registeredUserDTO.getId());
        userForUpdating.setName(registeredUserDTO.getName());
        //userForUpdating.setPassword(passwordEncoder.encode(registeredUserDTO.getPassword()));
        userForUpdating.setSurname(registeredUserDTO.getSurname());
        userForUpdating.setCity(registeredUserDTO.getCity());
        userForUpdating.setCountry(registeredUserDTO.getCountry());
        userForUpdating.setOccupation(registeredUserDTO.getOccupation());
        userForUpdating.setCompanyInfo(registeredUserDTO.getCompanyInfo());
        userForUpdating.setPhoneNumber(registeredUserDTO.getPhoneNumber());
        registeredUserService.updateRegisteredUser(userForUpdating);
        return new ResponseEntity<>(registeredUserDTO, HttpStatus.OK);
    }

    @PutMapping("/give-penal-points/{userId}")
    public int givePenalPoints(@PathVariable long userId) {
        return registeredUserService.givePenalPoints(userId);
    }

    @GetMapping("/get-users-penal-points/{id}")
    public ResponseEntity<Integer> getPenalPoints(@PathVariable long id){
        RegisteredUserDTO registeredUserDTO = new RegisteredUserDTO(registeredUserService.getRegisteredUser(id));
        return ResponseEntity.ok(registeredUserDTO.getPenal());
    }

    @PutMapping("/remove-penal-points/{id}")
    public ResponseEntity<Integer> removeUsersPenalPoints(@PathVariable long id){
        RegisteredUser registeredUser = registeredUserService.removeUsersPenalPoints(id);
        return ResponseEntity.ok(registeredUser.getPenalPoints());
    }
}
