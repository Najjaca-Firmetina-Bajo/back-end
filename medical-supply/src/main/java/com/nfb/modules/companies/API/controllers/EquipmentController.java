package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.EquipmentDto;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.usecases.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/filterByType/{type}")
    public ResponseEntity<List<EquipmentDto>> filter(@PathVariable String type) {

        List<Equipment> equipment = equipmentService.filterByType(type);

        List<EquipmentDto> equipmentDtos = new ArrayList<>();
        for (Equipment e : equipment) {
            equipmentDtos.add(new EquipmentDto(e));
        }

        return new ResponseEntity<>(equipmentDtos, HttpStatus.OK);
    }

    @GetMapping("/searchByName/{name}")
    public ResponseEntity<List<EquipmentDto>> search(@PathVariable String name) {

        List<Equipment> equipment = equipmentService.searchByName(name);

        List<EquipmentDto> equipmentDtos = new ArrayList<>();
        for (Equipment e : equipment) {
            equipmentDtos.add(new EquipmentDto(e));
        }

        return new ResponseEntity<>(equipmentDtos, HttpStatus.OK);
    }
}
