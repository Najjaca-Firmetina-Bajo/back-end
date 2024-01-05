package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.stakeholders.API.dtos.RegisteredUserDTO;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.domain.user.Role;
import com.nfb.modules.stakeholders.core.domain.user.User;
import com.nfb.modules.stakeholders.core.usecases.RegisteredUserService;
import com.nfb.modules.stakeholders.core.usecases.RoleService;
import com.nfb.modules.stakeholders.core.usecases.UserService;
import com.nfb.security.auth.ResourceConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/registeredUsers")
@CrossOrigin
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

    @GetMapping("/get_registered_user")
    public ResponseEntity<RegisteredUserDTO> getRegisteredUser(long id){
        RegisteredUserDTO registeredUserDTO = new RegisteredUserDTO(registeredUserService.getRegisteredUser(id));
        return ResponseEntity.ok(registeredUserDTO);
    }

    @PutMapping("/update-registered-user")
    public ResponseEntity<RegisteredUserDTO> updateRegisteredUser(@RequestBody RegisteredUserDTO registeredUserDTO){

        List<Role> roles = roleService.findByName("REGISTERED_USER");
        RegisteredUser userForUpdating = new RegisteredUser(
                registeredUserDTO.getEmail(),
                passwordEncoder.encode(registeredUserDTO.getPassword()),
                roles.get(0),
                registeredUserDTO.getName(),
                registeredUserDTO.getSurname(),
                registeredUserDTO.getCity(),
                registeredUserDTO.getCountry(),
                registeredUserDTO.getPhoneNumber(),
                registeredUserDTO.getOccupation(),
                registeredUserDTO.getCompanyInfo(),
                registeredUserDTO.getPenal()
        );
        userForUpdating.setId(registeredUserDTO.getId());
        registeredUserService.updateRegisteredUser(userForUpdating);
        return new ResponseEntity<>(registeredUserDTO, HttpStatus.OK);
    }
}
