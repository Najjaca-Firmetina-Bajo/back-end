package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.appointment.QREquipment;
import com.nfb.modules.companies.core.domain.appointment.QRStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface QREqipmentRepository extends JpaRepository<QREquipment, Long> {
    boolean existsByEquipment_Id(Long equipmentId);
    @Query("SELECT qe FROM QREquipment qe JOIN qe.qrCode qc JOIN qc.appointment a WHERE a.companyAdministrator.id = :companyAdministratorId")
    List<QREquipment> findAllByCompanyAdministratorId(@Param("companyAdministratorId") Long companyAdministratorId);

    @Query("SELECT qe FROM QREquipment qe " +
            "JOIN qe.qrCode qc " +
            "JOIN qc.appointment a " +
            "WHERE a.companyAdministrator.id = :companyAdministratorId " +
            "AND qc.status = 'NEW' " +
            "AND a.pickUpDate >= CURRENT_DATE")
    List<QREquipment> findAllByCompanyAdministratorIdAndStatusNowAndFuturePickupDate(@Param("companyAdministratorId") Long companyAdministratorId);
}