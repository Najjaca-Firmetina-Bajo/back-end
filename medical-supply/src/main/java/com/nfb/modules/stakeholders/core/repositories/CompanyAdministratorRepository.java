package com.nfb.modules.stakeholders.core.repositories;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyAdministratorRepository extends JpaRepository<CompanyAdministrator, Long>{
    List<CompanyAdministrator> findByCompany(Company company);
}
