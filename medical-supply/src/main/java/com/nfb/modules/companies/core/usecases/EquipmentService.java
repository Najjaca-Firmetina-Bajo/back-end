package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment add(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }
    public List<Equipment> getAll() { return equipmentRepository.findAll(); }
    public List<Equipment> filter(String name, String type, double minPrice, double maxPrice)
    {
        List<Equipment> filter = equipmentRepository.findFilteredEquipment(type, minPrice, maxPrice);
        List<Equipment> filterAndSearch = new ArrayList<>();

        for(Equipment e: filter) {
            if(e.getName().toLowerCase().contains(name.toLowerCase())) filterAndSearch.add(e);
        }

        return filterAndSearch;
    }
    public List<Equipment> search(String name) { return equipmentRepository.findByNameContainingIgnoreCase(name); }
    public List<Equipment> findByIdIn(List<Long> ids) { return equipmentRepository.findByIdIn(ids); }
}
