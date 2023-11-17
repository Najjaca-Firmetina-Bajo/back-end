package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Equipment> filterByType(String type) { return equipmentRepository.findByTypeContainingIgnoreCase(type); }
    public List<Equipment> searchByName(String name) { return equipmentRepository.findByNameContainingIgnoreCase(name); }
}
