package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.appointment.ExtraordinaryAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExtraordinaryAppointmentRepository extends JpaRepository<ExtraordinaryAppointmentRepository,Long> {

    @Query("SELECT e from ExtraordinaryAppointment e where e.isDownloaded = false and e.companyAdministrator in (:companyAdministrators) and DATE(e.pickUpDate) = :date")
    List<ExtraordinaryAppointment> getCompaniesNotDowloadedAppointments(List<Long> companyAdministrators, Date date);

    @Query("select a from ExtraordinaryAppointment a where a.companyAdministrator = :administratorId and a.pickUpDate = :date")
    ExtraordinaryAppointment checkIfAdministratorHasAppointment(long administratorId, Date date);
}
