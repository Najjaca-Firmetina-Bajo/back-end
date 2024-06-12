package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    List<QRCode> findByAppointmentId(long appointmentId);
    List<QRCode> findByRegisteredUser_Id(Long registeredUserId);
    boolean existsByReservedEquipmentInAndAppointment(List<Equipment> reservedEquipment, Appointment appointment);
    @Query("select q from QRCode q where q.registeredUser.id = :userId and q.status = 'PROCESSED' ")
    List<QRCode> getProcessedByUserId(Long userId);
    @Query("select q from QRCode q where q.registeredUser.id = :userId and q.status = 'NEW' ")
    List<QRCode> getNewByUserId(Long userId);
    @Query("select q from QRCode q where q.registeredUser.id = :userId and q.status = 'CANCELED' ")
    List<QRCode> getCanceledByUserId(Long userId);
    @Query("select q from QRCode q where q.registeredUser.id = :userId and q.status = 'DECLINED' ")
    List<QRCode> getDeclinedByUserId(Long userId);
    boolean existsByAppointmentId(Long appointmentId);
}