package com.nfb.modules.companies.API.controllers;

import com.nfb.modules.companies.API.dtos.CreateEquipmentDto;
import com.nfb.modules.companies.API.dtos.EquipmentDto;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.usecases.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@CrossOrigin
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/filter/{params}")
    public ResponseEntity<List<EquipmentDto>> filter(@PathVariable String params) {

        String[] parameters = params.split(",");
        String name = parameters[0];
        String type = parameters[1];
        double minPrice = Double.parseDouble(parameters[2]);
        double maxPrice = Double.parseDouble(parameters[3]);

        if(minPrice == 0) minPrice = -1;
        if(maxPrice == 0) maxPrice = -1;

        List<Equipment> equipment = equipmentService.filter(name, type, minPrice, maxPrice);

        List<EquipmentDto> equipmentDtos = new ArrayList<>();
        for (Equipment e : equipment) {
            equipmentDtos.add(new EquipmentDto(e));
        }

        return new ResponseEntity<>(equipmentDtos, HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<EquipmentDto>> search(@PathVariable String name) {

        List<Equipment> equipment = equipmentService.search(name);

        List<EquipmentDto> equipmentDtos = new ArrayList<>();
        for (Equipment e : equipment) {
            equipmentDtos.add(new EquipmentDto(e));
        }

        return new ResponseEntity<>(equipmentDtos, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EquipmentDto>> getAll() {

        List<Equipment> equipment = equipmentService.getAll();

        List<EquipmentDto> equipmentDtos = new ArrayList<>();
        for (Equipment e : equipment) {
            equipmentDtos.add(new EquipmentDto(e));
        }

        return new ResponseEntity<>(equipmentDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDto> getById(@PathVariable Long id) {
        Equipment equipment = equipmentService.getById(id);
        if (equipment != null) {
            return new ResponseEntity<>(new EquipmentDto(equipment), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByIds")
    public ResponseEntity<List<EquipmentDto>> getByIds(@RequestParam List<Long> ids) {
        List<Equipment> equipmentList = equipmentService.getByIds(ids);

        List<EquipmentDto> equipmentDtos = new ArrayList<>();
        for (Equipment e : equipmentList) {
            equipmentDtos.add(new EquipmentDto(e));
        }

        return new ResponseEntity<>(equipmentDtos, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody CreateEquipmentDto createEquipmentDto) {
        equipmentService.create(createEquipmentDto);
        return ResponseEntity.ok().build();
    }

}
