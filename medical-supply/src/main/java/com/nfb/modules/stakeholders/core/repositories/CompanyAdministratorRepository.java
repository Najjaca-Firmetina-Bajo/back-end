package com.nfb.modules.stakeholders.core.repositories;

import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyAdministratorRepository extends JpaRepository<CompanyAdministrator, Long>{
    List<CompanyAdministrator> findByCompany(Company company);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value ="3000")})
    @Query("SELECT a FROM CompanyAdministrator a WHERE a.id = :id")
    public CompanyAdministrator findOneById(@Param("id") Long id);

    @Modifying
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("UPDATE CompanyAdministrator ca SET ca.company = :company WHERE ca.id = :adminId")
    //@QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value ="0")})
    int setCompanyForAdministrator(@Param("adminId") Long adminId, @Param("company") Company company);

    @Query("SELECT ca FROM CompanyAdministrator ca WHERE ca.username = :email")
    CompanyAdministrator findByEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE CompanyAdministrator ca SET ca.passwordChanged = true WHERE ca.id = :adminId")
    @Transactional
    void updatePasswordChanged(@Param("adminId") Long adminId);
}
