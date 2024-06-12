package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.appointment.QREquipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QREqipmentRepository extends JpaRepository<QREquipment, Long> {
    boolean existsByEquipment_Id(Long equipmentId);

}