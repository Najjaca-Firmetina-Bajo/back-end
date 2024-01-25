package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    List<QRCode> findByAppointmentId(long appointmentId);
    boolean existsByReservedEquipmentInAndAppointment(List<Equipment> reservedEquipment, Appointment appointment);
}