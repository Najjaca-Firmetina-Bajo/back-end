package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.companies.core.usecases.CompanyService;
import com.nfb.modules.stakeholders.API.dtos.*;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.domain.user.Role;
import com.nfb.modules.stakeholders.core.domain.user.User;
import com.nfb.modules.stakeholders.core.usecases.RoleService;
import com.nfb.modules.stakeholders.core.usecases.UserService;
import com.nfb.security.auth.ResourceConflictException;
import com.nfb.security.auth.TokenUtils;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CompanyService companyService;

    // Prvi endpoint koji pogadja korisnik kada se loguje.
    // Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

    @GetMapping("/who-am-i")
    public ResponseEntity<Long> getAuthenticatedUserId(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return ResponseEntity.ok(user.getId());
        } else {
            // Handle the case where the user is not authenticated or not an instance of User
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    /*
    @GetMapping("/which-is-my-role")
    public ResponseEntity<List<Role>> getAuthenticatedUserRole(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return ResponseEntity.ok(user.getRoles());
        } else {
            // Handle the case where the user is not authenticated or not an instance of User
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    */
    // Endpoint za registraciju novog korisnika
    @PostMapping("/signup")
    public ResponseEntity<RegisteredUserDTO> save(@RequestBody UserDTO userRequest) {
        User existUser = this.userService.findByUsername(userRequest.getEmail());

        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getId(), "Username already exists");
        }

        List<Role> roles = roleService.findByName("REGISTERED_USER");
        RegisteredUser u = new RegisteredUser(
                userRequest.getEmail(),
                passwordEncoder.encode(userRequest.getPassword()),
                roles.get(0),
                userRequest.getName(),
                userRequest.getSurname(),
                userRequest.getCity(),
                userRequest.getCountry(),
                userRequest.getPhoneNumber(),
                userRequest.getOccupation(),
                userRequest.getCompanyInfo(),
                0
        );

        u = userService.register(u);
        return new ResponseEntity<>(new RegisteredUserDTO(u), HttpStatus.CREATED);
    }

    @PostMapping("/cadmin-signup")
    public ResponseEntity<CompanyAdministratorDto> registerCompanyAdmin(@RequestBody CompanyAdministratorDto companyAdministratorDto) {
        List<Role> roles = roleService.findByName("COMPANY_ADMINISTRATOR");
        CompanyAdministrator admin = new CompanyAdministrator(
                companyAdministratorDto.getEmail(),
                companyAdministratorDto.getPassword(),
                roles.get(0),
                companyAdministratorDto.getName(),
                companyAdministratorDto.getSurname(),
                companyAdministratorDto.getCity(),
                companyAdministratorDto.getCountry(),
                companyAdministratorDto.getPhoneNumber(),
                companyAdministratorDto.getOccupation(),
                companyAdministratorDto.getCompanyInfo(),
                companyService.findById(companyAdministratorDto.getCompanyId()).orElse(null)
        );
        admin = userService.register(admin);
        return new ResponseEntity<>(new CompanyAdministratorDto(admin), HttpStatus.CREATED);
    }

    @GetMapping("/activate/{id}")
    public ResponseEntity<String> validateUser(@PathVariable long id) {

        User validatedUser = userService.activateUser(id);

        String htmlMessage = "<html><body><h1>User Activated!</h1></body></html>";

        // Respond with HTML message
        return ResponseEntity.status(HttpStatus.OK)
                .body(htmlMessage);

    }
}
