package com.nfb.modules.companies.API.controllers;


import com.nfb.modules.companies.core.domain.company.CompanyRating;
import com.nfb.modules.companies.core.usecases.CompanyRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin
public class CompanyRatingController {

    @Autowired
    private CompanyRatingService companyRatingService;

    public CompanyRatingController(CompanyRatingService companyRatingService){
        this.companyRatingService = companyRatingService;
    }

    @PostMapping()
    public ResponseEntity<CompanyRating> rateCompany(@RequestBody CompanyRating rating){
        return new ResponseEntity<>(companyRatingService.save(rating), HttpStatus.OK);
    }
}
