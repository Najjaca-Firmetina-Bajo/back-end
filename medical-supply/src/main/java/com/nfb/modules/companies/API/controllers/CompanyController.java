package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.CompanyDto;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.usecases.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    //TODO: fix the companyEqipment adding
    /*@PostMapping("/register")
    public ResponseEntity<CompanyDto> registerCompany(@RequestBody CompanyDto companyDto) {
        Company company = companyService.prepareCompanyModel(companyDto.getId(), companyDto.getName(), companyDto.getAddress(), companyDto.getAverageRating(), companyDto.getAvailableEquipmentIds());
        company = companyService.register(company);
        return new ResponseEntity<>(new CompanyDto(company), HttpStatus.CREATED);
    }*/

    @GetMapping("/getCompaniesForEquipment/{companiesIds}")
    public ResponseEntity<List<CompanyDto>> getCompaniesForEquipment(@PathVariable List<Long> companiesIds) {

        List<Company> companies = companyService.findByIdIn(companiesIds);

        List<CompanyDto> companyDtos = new ArrayList<>();
        for (Company c : companies) {
            companyDtos.add(new CompanyDto(c));
        }

        return new ResponseEntity<>(companyDtos, HttpStatus.OK);
    }

    @GetMapping("/find/{companyName}")
    public ResponseEntity<CompanyDto> findByName(@PathVariable String companyName) {

        Company company = companyService.findByName(companyName);
        return new ResponseEntity<>(new CompanyDto(company), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CompanyDto>> getAll() {

        List<Company> companies = companyService.getAll();

        List<CompanyDto> companyDtos = new ArrayList<>();
        for (Company c : companies) {
            companyDtos.add(new CompanyDto(c));
        }

        return new ResponseEntity<>(companyDtos, HttpStatus.OK);
    }

    @PutMapping ("/add-company-admin/{companyId}/{adminId}")
    public ResponseEntity<CompanyDto> addAdministratorToCompany(@PathVariable long companyId, @PathVariable long adminId) {
        CompanyDto dto = new CompanyDto (companyService.addAdministratorToCompany(companyId, adminId));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/findById/{companyId}")
    public ResponseEntity<CompanyDto> findById(@PathVariable Long companyId) {
        Optional<Company> companyOptional = companyService.findById(companyId);

        if (companyOptional.isPresent()) {
            CompanyDto companyDto = new CompanyDto(companyOptional.get());
            return new ResponseEntity<>(companyDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
