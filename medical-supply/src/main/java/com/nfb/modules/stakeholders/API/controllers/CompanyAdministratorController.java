package com.nfb.modules.stakeholders.API.controllers;
import com.nfb.modules.companies.API.dtos.CompanyDto;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.usecases.CompanyService;
import com.nfb.modules.stakeholders.API.dtos.CompanyAdministratorDto;
import com.nfb.modules.stakeholders.API.dtos.SystemAdministratorDto;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.Role;
import com.nfb.modules.stakeholders.core.domain.user.SystemAdministrator;
import com.nfb.modules.stakeholders.core.usecases.CompanyAdministratorService;
import com.nfb.modules.stakeholders.core.usecases.RoleService;
import com.nfb.modules.stakeholders.core.usecases.SystemAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/companyAdministrators")
@CrossOrigin
public class CompanyAdministratorController {

    @Autowired
    private CompanyAdministratorService companyAdministratorService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private CompanyService companyService; //zbog razbijanja ciklicne zavisnosti CompanyAdministratorService - CompanyService

    public CompanyAdministratorController(CompanyAdministratorService companyAdministratorService, CompanyService companyService, RoleService roleService) {
        this.companyAdministratorService = companyAdministratorService;
        this.companyService = companyService;
        this.roleService = roleService;
    }

    @GetMapping ("/get-all")
    public ResponseEntity<List<CompanyAdministratorDto>> getAll() {
        List<CompanyAdministrator> administrators = companyAdministratorService.getAll();

        List<CompanyAdministratorDto> dtos = new ArrayList<>();
        for (CompanyAdministrator ca : administrators) {
            dtos.add(new CompanyAdministratorDto(ca));
        }

        return ResponseEntity.ok(dtos);
    }

    @PutMapping ("/set-company/{adminId}/{companyId}")
    public int setCompanyForAdministrator(@PathVariable long adminId, @PathVariable long companyId) {
        //Company company = companyService.findById(companyId).orElse(null);
        return companyAdministratorService.setCompanyForAdministrator(adminId, companyId);
    }

    @PutMapping ("/update-company")
    public ResponseEntity<Company> update(@RequestBody CompanyDto company) {
        return ResponseEntity.ok(companyService.update(company));
    }

}