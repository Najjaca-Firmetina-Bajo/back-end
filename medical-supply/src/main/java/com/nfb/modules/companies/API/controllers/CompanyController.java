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

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/register")
    public ResponseEntity<CompanyDto> registerCompany(@RequestBody CompanyDto companyDto) {
        Company company = companyService.prepareCompanyModel(companyDto.getId(), companyDto.getName(), companyDto.getAddress(), companyDto.getAverageRating(), companyDto.getAvailableEquipmentIds());
        company = companyService.register(company);
        return new ResponseEntity<>(new CompanyDto(company), HttpStatus.CREATED);
    }

    @GetMapping("/getCompaniesForEquipment/{companiesIds}")
    public ResponseEntity<List<CompanyDto>> getCompaniesForEquipment(@PathVariable List<Long> companiesIds) {

        List<Company> companies = companyService.findByIdIn(companiesIds);

        List<CompanyDto> companyDtos = new ArrayList<>();
        for (Company c : companies) {
            companyDtos.add(new CompanyDto(c));
        }

        return new ResponseEntity<>(companyDtos, HttpStatus.OK);
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
}
