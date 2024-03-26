package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByWorkingDayId(long workingDayId);
    Appointment findById(long id);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value ="1000")})
    @Query("SELECT a FROM Appointment a WHERE a.id = :id")
    public Appointment findOneById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Appointment a SET a.isDownloaded = :isDownloaded WHERE a.id = :id")
    @Transactional
    void updateIsDownloaded(@Param("isDownloaded") boolean isDownloaded, @Param("id") long id);

    @Modifying
    @Query("UPDATE Appointment a SET a.downloadedAt = :downloadedAt WHERE a.id = :id")
    @Transactional
    void updateDownloadedAt(@Param("downloadedAt") LocalDateTime downloadedAt, @Param("id") long id);

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value ="1000")})
    @Query("SELECT a FROM Appointment a WHERE a.companyAdministrator =:companyAdministrator")
    List<Appointment> findAppointmentsWithCompanyAdministrator(@Param("companyAdministrator") CompanyAdministrator companyAdministrator);

    @Query("SELECT a FROM Appointment a WHERE a.pickUpDate < :currentDate")
    List<Appointment> findExpiredAppointments(@Param("currentDate") LocalDateTime currentDate);

    @Query("SELECT a FROM Appointment a WHERE a.pickUpDate >= :currentDate AND a.isDownloaded = false")
    List<Appointment> findNonExpiredNotDownloadedAppointments(@Param("currentDate") LocalDateTime currentDate);

    @Query("SELECT a FROM Appointment a WHERE a.isDownloaded = true")
    List<Appointment> findDownloadedAppointments();

    @Query("select a from Appointment a where a.companyAdministrator.id = :administratorId and DATE(a.pickUpDate) = :date")
    Appointment checkIfAdministratorHasAppointment(long administratorId, Date date);

    @Query("SELECT e from Appointment e where e.isDownloaded = false and e.type = 1 and e.companyAdministrator.id in (:companyAdministrators) and DATE(e.pickUpDate) = :date")
    List<Appointment> getCompaniesNotDowloadedAppointments(List<Long> companyAdministrators, Date date);
}
