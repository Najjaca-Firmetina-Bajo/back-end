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

    @PostMapping("/register")
    public ResponseEntity<CompanyDto> registerCompany(@RequestBody CompanyDto companyDto) {
        Company company = companyService.prepareCompanyModel(companyDto.getId(), companyDto.getName(), companyDto.getAddress(), companyDto.getAverageRating());
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

    @GetMapping("/search/{nameOrPlace}")
    public ResponseEntity<List<CompanyDto>> search(@PathVariable String nameOrPlace) {

        List<Company> companies = companyService.search(nameOrPlace);

        List<CompanyDto> companyDtos = new ArrayList<>();
        for (Company c : companies) {
            companyDtos.add(new CompanyDto(c));
        }

        return new ResponseEntity<>(companyDtos, HttpStatus.OK);
    }

    @GetMapping("/filter/{params}")
    public ResponseEntity<?> filterForRating(@PathVariable String params) {
        try {
            String[] parameters = params.split(",");

            // Provera da li su uneti svi potrebni podaci za filtriranje
            if (parameters.length < 2) {
                return new ResponseEntity<>("Nedovoljno podataka za filtriranje.", HttpStatus.BAD_REQUEST);
            }

            String nameOrPlace = parameters[0];

            // Provera da li se može parsirati ocena kao double
            double grade;
            try {
                grade = Double.parseDouble(parameters[1]);
            } catch (NumberFormatException e) {
                return new ResponseEntity<>("Neuspešno parsiranje ocene.", HttpStatus.BAD_REQUEST);
            }

            List<Company> companies = companyService.filter(nameOrPlace, grade);

            List<CompanyDto> companyDtos = new ArrayList<>();
            for (Company c : companies) {
                companyDtos.add(new CompanyDto(c));
            }

            return new ResponseEntity<>(companyDtos, HttpStatus.OK);
        } catch (Exception e) {
            // Uhvaćene neke nepredviđene greške
            return new ResponseEntity<>("Došlo je do greške prilikom obrade zahteva.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter-eq/{params}")
    public ResponseEntity<?> filterForEquipment(@PathVariable String params) {
        try {
            String[] parameters = params.split(",");

            // Provera da li su uneti svi potrebni podaci za filtriranje
            if (parameters.length < 2) {
                return new ResponseEntity<>("Nedovoljno podataka za filtriranje.", HttpStatus.BAD_REQUEST);
            }

            String nameOrPlace = parameters[0];

            // Provera da li se može parsirati ocena kao double
            int equipmentCount;
            try {
                equipmentCount = Integer.parseInt(parameters[1]);
            } catch (NumberFormatException e) {
                return new ResponseEntity<>("Neuspešno parsiranje ocene.", HttpStatus.BAD_REQUEST);
            }

            List<Company> companies = companyService.filterByEquipmentCount(nameOrPlace, equipmentCount);

            List<CompanyDto> companyDtos = new ArrayList<>();
            for (Company c : companies) {
                companyDtos.add(new CompanyDto(c));
            }

            return new ResponseEntity<>(companyDtos, HttpStatus.OK);
        } catch (Exception e) {
            // Uhvaćene neke nepredviđene greške
            return new ResponseEntity<>("Došlo je do greške prilikom obrade zahteva.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sort/{ascOrDesc}/{type}")
    public ResponseEntity<List<CompanyDto>> sort(@PathVariable String ascOrDesc, @PathVariable String type) {
        List<Company> sortedCompanies = companyService.sortCompanies(ascOrDesc,type);

        if (!sortedCompanies.isEmpty()) {
            List<CompanyDto> companyDtos = new ArrayList<>();
            for (Company c : sortedCompanies) {
                companyDtos.add(new CompanyDto(c));
            }

            return new ResponseEntity<>(companyDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/check-if-user-can-rate/{userId}/{companyId}")
    public ResponseEntity<Boolean> CheckIfUserCanRateComp(@PathVariable long userId, @PathVariable long companyId){
        return new ResponseEntity<>(companyService.CheckIfUserCanRateComp(userId,companyId),HttpStatus.OK);
    }
}
