package com.nfb.modules.stakeholders.core.repositories;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyAdministratorRepository extends JpaRepository<CompanyAdministrator, Long>{
    List<CompanyAdministrator> findByCompany(Company company);

    @Modifying
    @Query("UPDATE CompanyAdministrator ca SET ca.company = :company WHERE ca.id = :adminId")
    int setCompanyForAdministrator(@Param("adminId") Long adminId, @Param("company") Company company);
}
