package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
