package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByTypeContainingIgnoreCase(String type);
    List<Equipment> findByNameContainingIgnoreCase(String name);
    List<Equipment> findByIdIn(List<Long> ids);
}
