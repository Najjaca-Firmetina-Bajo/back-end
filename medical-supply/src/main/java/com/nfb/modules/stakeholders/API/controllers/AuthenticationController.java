package com.nfb.modules.stakeholders.API.controllers;

import com.nfb.modules.companies.core.usecases.CompanyService;
import com.nfb.modules.stakeholders.API.dtos.*;
import com.nfb.modules.stakeholders.core.domain.user.*;
import com.nfb.modules.stakeholders.core.usecases.CompanyAdministratorService;
import com.nfb.modules.stakeholders.core.usecases.RoleService;
import com.nfb.modules.stakeholders.core.usecases.SystemAdministratorService;
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
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private CompanyAdministratorService companyAdministratorService;
    @Autowired
    private SystemAdministratorService systemAdministratorService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

    @GetMapping("/who-am-i")
    public ResponseEntity<Long> getAuthenticatedUserId(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return ResponseEntity.ok(user.getId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/who-am-i-detailed")
    public ResponseEntity<UserDetailsDto> getAuthenticatedUserDetails(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            Role role = user.getRoles().get(0);
            String rolestr = (role != null) ? role.getName() : "";
            UserDetailsDto userDetailsDto = new UserDetailsDto(user.getId(), user.getUsername(), rolestr);
            return ResponseEntity.ok(userDetailsDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

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

    @PostMapping("/register-company-admin")
    public ResponseEntity<CompanyAdministratorDto> registerSystemAdmin(@RequestBody CompanyAdministratorDto companyAdministratorDto) {
        List<Role> roles = roleService.findByName("COMPANY_ADMINISTRATOR");
        CompanyAdministrator admin = new CompanyAdministrator(
                companyAdministratorDto.getEmail(),
                passwordEncoder.encode(companyAdministratorDto.getPassword()),
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
        admin = companyAdministratorService.register(admin);

        return new ResponseEntity<>(new CompanyAdministratorDto(admin), HttpStatus.CREATED);
    }

    @PostMapping("/register-system-admin")
    public ResponseEntity<SystemAdministratorDto> registerSystemAdmin(@RequestBody SystemAdministratorDto systemAdministratorDto) {
        List<Role> roles = roleService.findByName("SYSTEM_ADMINISTRATOR");
        SystemAdministrator admin = new SystemAdministrator(
                systemAdministratorDto.getEmail(),
                passwordEncoder.encode(systemAdministratorDto.getPassword()),
                roles.get(0),
                systemAdministratorDto.getName(),
                systemAdministratorDto.getSurname(),
                systemAdministratorDto.getCity(),
                systemAdministratorDto.getCountry(),
                systemAdministratorDto.getPhoneNumber(),
                systemAdministratorDto.getOccupation(),
                systemAdministratorDto.getCompanyInfo()
        );
        admin = systemAdministratorService.register(admin);
        return new ResponseEntity<>(new SystemAdministratorDto(admin), HttpStatus.CREATED);
    }

    @GetMapping(value = "/activate/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> validateUser(@PathVariable long id) {
        User validatedUser = userService.activateUser(id);

        // HTML content with centered div and link to return to app
        String htmlMessage = "<html><head><style>" +
                "body { display: flex; align-items: center; justify-content: center; height: 100vh; margin: 0; }" +
                "div { text-align: center; }" +
                "</style></head><body><div>" +
                "<h1>User Activated!</h1>" +
                "<a href='http://localhost:4200'>Return to App</a>" +
                "</div></body></html>";

        // Respond with HTML message
        return ResponseEntity.status(HttpStatus.OK)
                .body(htmlMessage);
    }

    /*
    @PutMapping ("/update-password/{password}/{username}")
    public ResponseEntity<UserDTO> addAdministratorToCompany(Authentication authentication, @PathVariable String password, @PathVariable String username) {
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            String encodedPassword = passwordEncoder.encode(password);
            UserDTO dto = new UserDTO (userService.updatePassword(encodedPassword, username));
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    */
}
