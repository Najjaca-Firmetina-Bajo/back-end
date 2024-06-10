package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.equipment.Equipment;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @Query("SELECT e FROM Equipment e WHERE " +
            "(COALESCE(:type, '') = '' OR LOWER(e.type) LIKE LOWER(CONCAT('%', :type, '%'))) AND " +
            "(:minPrice < 0 OR e.price >= :minPrice) AND " +
            "(:maxPrice < 0 OR e.price <= :maxPrice)")
    List<Equipment> findFilteredEquipment(@Param("type") String type, @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
    List<Equipment> findByNameContainingIgnoreCase(String name);
    List<Equipment> findByIdIn(List<Long> ids);
    /*
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select e from Equipment e where e.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    @Override
    Optional<Equipment> findById(@Param("id")Long id);
    */

    @Modifying
    @Transactional
    @Query("UPDATE Equipment e SET e.price = :price, e.description = :description, e.name = :name, e.type = :type WHERE e.id = :id")
    void update(@Param("id") Long id, @Param("price") double price, @Param("description") String description, @Param("name") String name, @Param("type") String type);

}
