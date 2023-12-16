package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @Query("SELECT e FROM Equipment e WHERE " +
            "(COALESCE(:type, '') = '' OR LOWER(e.type) LIKE LOWER(CONCAT('%', :type, '%'))) AND " +
            "(:minPrice < 0 OR e.price >= :minPrice) AND " +
            "(:maxPrice < 0 OR e.price <= :maxPrice)")
    List<Equipment> findFilteredEquipment(@Param("type") String type, @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
    List<Equipment> findByNameContainingIgnoreCase(String name);
    List<Equipment> findByIdIn(List<Long> ids);
}
