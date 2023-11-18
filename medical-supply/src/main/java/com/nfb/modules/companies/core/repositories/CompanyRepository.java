package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByIdIn(List<Long> ids);
}
