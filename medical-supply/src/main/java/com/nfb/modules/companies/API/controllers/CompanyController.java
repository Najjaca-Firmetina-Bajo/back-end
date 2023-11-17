package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.CompanyDto;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.usecases.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/register")
    public ResponseEntity<CompanyDto> registerCompany(@RequestBody CompanyDto companyDto) {
        Company company = new Company(companyDto.getName(), companyDto.getAddress(), companyDto.getAverageRating());
        company = companyService.register(company);
        return new ResponseEntity<>(new CompanyDto(company), HttpStatus.CREATED);
    }
}
