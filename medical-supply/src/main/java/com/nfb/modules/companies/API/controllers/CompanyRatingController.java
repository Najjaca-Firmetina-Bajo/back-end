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

    @PutMapping()
    public ResponseEntity<CompanyRatingDto> updateRating(@RequestBody CompanyRatingDto ratingDto){
        CompanyRating cr = companyRatingService.findUsersRatingForCompany(ratingDto.getCompanyId(),ratingDto.getUserId());
        cr.setRating(ratingDto.getRating());
        cr.setRatingReasons(ratingDto.getRatingReasons());
        cr.setRatingDescription(ratingDto.getRatingDescription());
        companyRatingService.save(cr);

        return new ResponseEntity<>(ratingDto, HttpStatus.OK);
    }

    @GetMapping("/get-users-rating-for-comp/{companyId}/{userId}")
    public ResponseEntity<CompanyRatingDto> findUsersRatingForCompany(@PathVariable long companyId, @PathVariable long userId){
        CompanyRating cr = companyRatingService.findUsersRatingForCompany(companyId,userId);
        CompanyRatingDto companyRatingDto = new CompanyRatingDto(cr.getRating(),cr.getRatingReasons(),cr.getRatingDescription(),cr.getCompany().getId(),cr.getUser().getId());
        return new ResponseEntity<>(companyRatingDto,HttpStatus.OK);
    }
}
