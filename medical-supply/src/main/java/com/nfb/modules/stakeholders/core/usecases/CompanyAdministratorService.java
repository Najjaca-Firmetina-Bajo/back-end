package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.UserRole;
import com.nfb.modules.stakeholders.core.repositories.CompanyAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyAdministratorService {
    private final CompanyAdministratorRepository companyAdministratorRepository;

    @Autowired
    public CompanyAdministratorService(CompanyAdministratorRepository companyAdministratorRepository) {
        this.companyAdministratorRepository = companyAdministratorRepository;
    }
    public List<CompanyAdministrator> findByCompanyAndRole(Company company, UserRole role) { return companyAdministratorRepository.findByCompanyAndRole(company, role); }

}
