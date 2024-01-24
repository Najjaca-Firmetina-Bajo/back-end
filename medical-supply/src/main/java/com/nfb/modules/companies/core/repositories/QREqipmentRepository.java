package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.domain.appointment.QREquipment;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QREqipmentRepository extends JpaRepository<QREquipment, Long> {

}