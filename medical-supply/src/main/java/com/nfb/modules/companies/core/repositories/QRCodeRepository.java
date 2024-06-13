package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Modifying
    @Query("update QRCode q set q.status = 'PROCESSED' where q.id = :qrCodeId")
    int updateStatusToProcessed(Long qrCodeId);

    @Transactional
    @Modifying
    @Query("update QRCode q set q.status = 'EXPIRED' where q.id = :qrCodeId")
    int updateStatusToExpired(Long qrCodeId);

    @Query("SELECT YEAR(a.pickUpDate) AS year, COALESCE(COUNT(q), 0) AS count " +
            "FROM Appointment a " +
            "LEFT JOIN QRCode q ON q.appointment.id = a.id " +
            "WHERE a.id IN :appointmentIds " +
            "GROUP BY YEAR(a.pickUpDate)")
    List<Object[]> countQRCodesByYear(@Param("appointmentIds") List<Long> appointmentIds);

    @Query("SELECT CEIL(MONTH(q.appointment.pickUpDate)/3) AS quarter, COUNT(q) AS count " +
            "FROM QRCode q " +
            "WHERE q.appointment.id IN :appointmentIds AND YEAR(q.appointment.pickUpDate) = :year " +
            "GROUP BY CEIL(MONTH(q.appointment.pickUpDate)/3)")
    List<Object[]> countQRCodesByQuarter(@Param("appointmentIds") List<Long> appointmentIds, @Param("year") int year);

    @Query("SELECT MONTH(q.appointment.pickUpDate) AS month, COUNT(q) AS count " +
            "FROM QRCode q " +
            "WHERE q.appointment.id IN :appointmentIds AND YEAR(q.appointment.pickUpDate) = :year " +
            "GROUP BY MONTH(q.appointment.pickUpDate)")
    List<Object[]> countQRCodesByMonth(@Param("appointmentIds") List<Long> appointmentIds, @Param("year") int year);

    @Query("SELECT q FROM QRCode q WHERE q.appointment.id IN :appointmentIds")
    List<QRCode> findQRCodesByAppointmentIds(@Param("appointmentIds") List<Long> appointmentIds);

    @Query("SELECT q FROM QRCode q WHERE q.appointment.id IN :appointmentIds AND q.status = 'PROCESSED'")
    List<QRCode> findProcessedQRCodesByAppointmentIds(@Param("appointmentIds") List<Long> appointmentIds);

}