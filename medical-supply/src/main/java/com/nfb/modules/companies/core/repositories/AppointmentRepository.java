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

    @Query("SELECT e from Appointment e where e.isDownloaded = false and e.companyAdministrator.id in (:companyAdministrators) and DATE(e.pickUpDate) = :date and e.winnerId = -1")
    List<Appointment> getCompaniesNotDowloadedAppointments(List<Long> companyAdministrators, Date date);

    @Query("SELECT a from Appointment a where a.winnerId = :winnerId and a.isDownloaded = true")
    List<Appointment> getUsersDownloadedAppointments(long winnerId);

    @Query("select a from Appointment a where a.winnerId = :winnerId and a.isDownloaded = true order by a.pickUpDate desc")
    List<Appointment> sortAppointmentsByDateDesc(long winnerId);

    @Query("select a from Appointment a where a.winnerId = :winnerId and a.isDownloaded = true order by a.pickUpDate asc")
    List<Appointment> sortAppointmentsByDateAsc(long winnerId);

    @Query("select a from Appointment a where a.winnerId = :winnerId and a.isDownloaded = true order by a.duration desc")
    List<Appointment> sortAppointmentsByDurationDesc(long winnerId);

    @Query("select a from Appointment a where a.winnerId = :winnerId and a.isDownloaded = true order by a.duration asc")
    List<Appointment> sortAppointmentsByDurationAsc(long winnerId);

    List<Appointment> findAllByCompanyAdministratorId(Long companyAdministratorId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Appointment a WHERE a.id = :id")
    void deleteAppointmentById(@Param("id") Long id);

    @Query("SELECT a FROM Appointment a " +
            "WHERE a.companyAdministrator.id = :companyAdministratorId " +
            "AND a.pickUpDate > CURRENT_TIMESTAMP " +
            "AND a.id NOT IN (SELECT q.appointment.id FROM QRCode q)")
    List<Appointment> findFutureAppointmentsWithoutQRCodes(@Param("companyAdministratorId") Long companyAdministratorId);

    @Query("SELECT a FROM Appointment a WHERE a.companyAdministrator.id IN :adminIds")
    List<Appointment> findAllByCompanyAdministratorIds(@Param("adminIds") List<Long> adminIds);

    @Query("SELECT YEAR(a.pickUpDate) AS year, COUNT(a) AS count FROM Appointment a WHERE a.companyAdministrator.id IN :adminIds GROUP BY YEAR(a.pickUpDate)")
    List<Object[]> countAppointmentsByYear(@Param("adminIds") List<Long> adminIds);

    @Query("SELECT CEIL(MONTH(a.pickUpDate)/3) AS quarter, COUNT(a) AS count FROM Appointment a WHERE a.companyAdministrator.id IN :adminIds AND YEAR(a.pickUpDate) = :year GROUP BY CEIL(MONTH(a.pickUpDate)/3)")
    List<Object[]> countAppointmentsByQuarter(@Param("adminIds") List<Long> adminIds, @Param("year") int year);

    @Query("SELECT MONTH(a.pickUpDate) AS month, COUNT(a) AS count FROM Appointment a WHERE a.companyAdministrator.id IN :adminIds AND YEAR(a.pickUpDate) = :year GROUP BY MONTH(a.pickUpDate)")
    List<Object[]> countAppointmentsByMonth(@Param("adminIds") List<Long> adminIds, @Param("year") int year);

    @Query("SELECT a FROM Appointment a " +
            "WHERE a.companyAdministrator.id IN :adminIds " +
            "AND a.pickUpDate BETWEEN :startDate AND :endDate")
    List<Appointment> findAppointmentsByAdminIdsAndDateRange(@Param("adminIds") List<Long> adminIds,
                                                             @Param("startDate") LocalDateTime startDate,
                                                             @Param("endDate") LocalDateTime endDate);
}
