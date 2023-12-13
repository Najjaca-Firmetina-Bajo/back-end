package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByIdIn(List<Long> ids);

    @Modifying
    @Transactional
    @Query("UPDATE Company c SET c.administrators = :administrators WHERE c.id = :companyId")
    void addAdministratorToCompany(@Param("companyId") long companyId, @Param("administrators") List<CompanyAdministrator> administrators);
}
