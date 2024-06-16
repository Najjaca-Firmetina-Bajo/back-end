package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.ContractInfoDto;
import com.nfb.modules.companies.core.usecases.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
@CrossOrigin
public class ContractController {
    @Autowired
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/get-all-by-company/{companyId}")
    public ResponseEntity<List<ContractInfoDto>> getAllByCompanyId(@PathVariable long companyId) {
        return ResponseEntity.ok(contractService.getAllByCompanyId(companyId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractInfoDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(contractService.getById(id));
    }

    @PutMapping("/deliver/{id}")
    public ResponseEntity<Void> deliver(@PathVariable long id) {
        contractService.deliver(id);
        return ResponseEntity.ok().build();
    }
}
