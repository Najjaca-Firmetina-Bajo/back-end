package com.nfb.modules.companies.core.repositories;

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
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByIdIn(List<Long> ids);

    Company findByName(String name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value ="0")})
    @Query("SELECT c from Company c where c.id = :id")
    public Company findOneById(@Param("id")Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Company c SET c.administrators = :administrators WHERE c.id = :companyId")
    void addAdministratorToCompany(@Param("companyId") long companyId, @Param("administrators") List<CompanyAdministrator> administrators);
}
