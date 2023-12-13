package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByTypeContainingAndPriceBetween(String type, double minPrice, double maxPrice);

    default List<Equipment> findByTypeAndPriceRangeWithNullHandling(String type, double minPrice, double maxPrice) {
        if (type == null || type.isEmpty()) {
            type = "%";
        }

        if (minPrice <= 0) {
            minPrice = -1.0;
        }

        if (maxPrice <= 0) {
            maxPrice = -1.0;
        }

        return findByTypeContainingAndPriceBetween(type, minPrice, maxPrice);
    }

    List<Equipment> findByNameContainingIgnoreCase(String name);
    List<Equipment> findByIdIn(List<Long> ids);
}
