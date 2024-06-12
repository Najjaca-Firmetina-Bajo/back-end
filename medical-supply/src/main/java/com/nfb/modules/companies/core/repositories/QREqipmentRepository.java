package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.appointment.QREquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QREqipmentRepository extends JpaRepository<QREquipment, Long> {
    boolean existsByEquipment_Id(Long equipmentId);
    @Query("SELECT qe FROM QREquipment qe JOIN qe.qrCode qc JOIN qc.appointment a WHERE a.companyAdministrator.id = :companyAdministratorId")
    List<QREquipment> findAllByCompanyAdministratorId(@Param("companyAdministratorId") Long companyAdministratorId);

}