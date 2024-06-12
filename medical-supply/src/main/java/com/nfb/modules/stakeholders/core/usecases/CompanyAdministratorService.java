package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import com.nfb.modules.companies.core.usecases.CompanyService;
import com.nfb.modules.stakeholders.API.dtos.AdminCompanyLoggingDto;
import com.nfb.modules.stakeholders.API.dtos.AdminInfoDto;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.SystemAdministrator;
import com.nfb.modules.stakeholders.core.repositories.CompanyAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyAdministratorService {
    private final CompanyAdministratorRepository companyAdministratorRepository;
    private final CompanyRepository companyRepository;
    private final UserService userService;

    @Autowired
    public CompanyAdministratorService(CompanyAdministratorRepository companyAdministratorRepository, UserService userService, CompanyRepository companyRepository) {
        this.companyAdministratorRepository = companyAdministratorRepository;
        this.userService = userService;
        this.companyRepository = companyRepository;
    }

    public CompanyAdministrator register(CompanyAdministrator companyAdministrator) {
        CompanyAdministrator ca = companyAdministratorRepository.save(companyAdministrator);
        userService.activateUser(ca.getId());
        return ca;
    }

    public List<CompanyAdministrator> findByCompany(Company company) { return companyAdministratorRepository.findByCompany(company); }
    public List<CompanyAdministrator> getAll() { return companyAdministratorRepository.findAll(); }
    /*
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public int setCompanyForAdministrator(long adminId, Company company) {
        return companyAdministratorRepository.setCompanyForAdministrator(adminId, company);
    }*/

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public int setCompanyForAdministrator(long adminId, long companyId) {
        Company company = companyRepository.findOneById(companyId);
        return companyAdministratorRepository.setCompanyForAdministrator(adminId, company);
    }

    public Optional<CompanyAdministrator> findById(long id) { return companyAdministratorRepository.findById(id); }

    public void update(AdminInfoDto adminInfoDto) {
        userService.update(adminInfoDto);
    }

    public AdminCompanyLoggingDto getLoggingInfo(String email) {
        CompanyAdministrator companyAdministrator = companyAdministratorRepository.findByEmail(email);

        return new AdminCompanyLoggingDto(companyAdministrator.getId(),
                                                        companyAdministrator.getUsername(), companyAdministrator.isPasswordChanged());
    }

    public void updatePasswordChanged(Long adminId) { companyAdministratorRepository.updatePasswordChanged(adminId); }
}
