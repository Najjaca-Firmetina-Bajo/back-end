package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.core.usecases.CompanyEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companyEquipment")
public class CompanyEquipmentController {

    @Autowired
    private CompanyEquipmentService companyEquipmentService;

    public CompanyEquipmentController(CompanyEquipmentService companyEquipmentService) {
        this.companyEquipmentService = companyEquipmentService;
    }

    @PutMapping("/pick-up-equipment/{equipmentId}/{companyId}/{quantity}")
    public int pickUpEquipment(@PathVariable long equipmentId, @PathVariable long companyId, @PathVariable int quantity) {
        return companyEquipmentService.pickUpEquipment(equipmentId, companyId, quantity);
    }
}
