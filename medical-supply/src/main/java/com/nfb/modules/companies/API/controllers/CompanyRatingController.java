package com.nfb.modules.companies.API.controllers;


import com.nfb.modules.companies.API.dtos.CompanyRatingDto;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.company.CompanyRating;
import com.nfb.modules.companies.core.usecases.CompanyRatingService;
import com.nfb.modules.companies.core.usecases.CompanyService;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.usecases.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin
public class CompanyRatingController {

    @Autowired
    private CompanyRatingService companyRatingService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private RegisteredUserService registeredUserService;

    public CompanyRatingController(CompanyRatingService companyRatingService, CompanyService companyService, RegisteredUserService registeredUserService){
        this.companyRatingService = companyRatingService;
        this.companyService = companyService;
        this.registeredUserService = registeredUserService;
    }

    @PostMapping()
    public ResponseEntity<CompanyRatingDto> rateCompany(@RequestBody CompanyRatingDto ratingDto){
        CompanyRating rating = new CompanyRating(ratingDto.getRating(),ratingDto.getRatingReasons(),ratingDto.getRatingDescription(),companyService.getById(ratingDto.getCompanyId()), registeredUserService.getRegisteredUser(ratingDto.getUserId()));
        companyRatingService.save(rating);

        return new ResponseEntity<>(ratingDto, HttpStatus.OK);
    }
}
