package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByWorkingDayId(long workingDayId);
    Appointment findById(long id);

    @Modifying
    @Query("UPDATE Appointment a SET a.isDownloaded = :isDownloaded WHERE a.id = :id")
    @Transactional
    void updateIsDownloaded(@Param("isDownloaded") boolean isDownloaded, @Param("id") long id);

    @Query("SELECT a FROM Appointment a WHERE a.pickUpDate < :currentDate")
    List<Appointment> findExpiredAppointments(@Param("currentDate") LocalDateTime currentDate);

    @Query("SELECT a FROM Appointment a WHERE a.pickUpDate >= :currentDate AND a.isDownloaded = false")
    List<Appointment> findNonExpiredNotDownloadedAppointments(@Param("currentDate") LocalDateTime currentDate);


}
