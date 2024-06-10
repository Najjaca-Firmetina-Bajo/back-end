package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.stakeholders.API.dtos.ResetPasswordDto;
import com.nfb.modules.stakeholders.API.dtos.UserDTO;
import com.nfb.modules.stakeholders.core.domain.user.Role;
import com.nfb.modules.stakeholders.core.usecases.UserService;
import com.nfb.modules.stakeholders.core.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/get-all")
    public ResponseEntity<List<UserDTO>> getUsers() {

        List<User> users = userService.getAll();

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(new UserDTO(user));
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @GetMapping("/is-system-administrator/{username}")
    public boolean isSystemAdministrator(@PathVariable String username) {
        User user = userService.findByUsername(username);
        for(Role r: user.getRoles()) {
            if(r.getName().equals("SYSTEM_ADMINISTRATOR")) return true;
        }
        return false;
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        String encodedPassword = passwordEncoder.encode(resetPasswordDto.getNewPassword());
        String encodedOldPassword = passwordEncoder.encode(resetPasswordDto.getOldPassword());
        boolean isOldPasswordValid = userService.checkOldPassword(encodedOldPassword, resetPasswordDto.getId());

        if (!isOldPasswordValid) {
            // Ako stara lozinka nije validna, vratite odgovarajući odgovor sa statusom greške
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password is invalid.");
        }

        userService.updatePassword(encodedPassword, resetPasswordDto.getId());

        // Ako je lozinka uspešno resetovana, vratite odgovarajući odgovor sa statusom OK
        return ResponseEntity.ok().build();
    }
}
