package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.company.CompanyEquipment;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyEquipmentRepository extends JpaRepository<CompanyEquipment, Long> {

    @Modifying
    @Query("UPDATE CompanyEquipment ce SET ce.quantity = :quantity WHERE ce.equipment.id = :equipmentId AND ce.company.id = :companyId")
    @Transactional
    void updateQuantity(@Param("equipmentId") long equipmentId, @Param("companyId") long companyId, @Param("quantity") int quantity);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value ="500")})
    @Query("SELECT ce FROM CompanyEquipment ce WHERE ce.company.id = :companyId AND ce.equipment.id = :equipmentId")
    CompanyEquipment findByCompanyIdAndEquipmentId(@Param("companyId") Long companyId, @Param("equipmentId") Long equipmentId);

}
