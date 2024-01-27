package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.usecases.CompanyService;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.SystemAdministrator;
import com.nfb.modules.stakeholders.core.repositories.CompanyAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyAdministratorService {
    private final CompanyAdministratorRepository companyAdministratorRepository;
    private final UserService userService;

    @Autowired
    public CompanyAdministratorService(CompanyAdministratorRepository companyAdministratorRepository, UserService userService) {
        this.companyAdministratorRepository = companyAdministratorRepository;
        this.userService = userService;
    }

    public CompanyAdministrator register(CompanyAdministrator companyAdministrator) {
        CompanyAdministrator ca = companyAdministratorRepository.save(companyAdministrator);
        userService.activateUser(ca.getId());
        return ca;
    }

    public List<CompanyAdministrator> findByCompany(Company company) { return companyAdministratorRepository.findByCompany(company); }
    public List<CompanyAdministrator> getAll() { return companyAdministratorRepository.findAll(); }
    public int setCompanyForAdministrator(long adminId, Company company) { return companyAdministratorRepository.setCompanyForAdministrator(adminId, company); }
    public Optional<CompanyAdministrator> findById(long id) { return companyAdministratorRepository.findById(id); }
}
