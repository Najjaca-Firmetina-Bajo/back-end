package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.User;
import com.nfb.modules.stakeholders.core.domain.user.UserRole;
import com.nfb.modules.stakeholders.core.usecases.CompanyAdministratorService;
import com.nfb.modules.stakeholders.core.usecases.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService   {
    private final CompanyRepository companyRepository;
    private final EquipmentService equipmentService;
    private final CompanyAdministratorService companyAdministratorService;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, EquipmentService equipmentService, CompanyAdministratorService companyAdministratorService) {
        this.companyRepository = companyRepository;
        this.equipmentService = equipmentService;
        this.companyAdministratorService = companyAdministratorService;
    }

    public Company register(Company company) { return companyRepository.save(company); }
    public List<Company> getAll() { return companyRepository.findAll(); }
    public Company prepareCompanyModel(Long companyId, String name, String address, double rating, List<Long> availableEquipmentIds) {
        List<Equipment> availableEquipment = equipmentService.findByIdIn(availableEquipmentIds);
        Company company = companyRepository.findById(companyId).orElse(null);
        List<CompanyAdministrator> admins = companyAdministratorService.findByCompanyAndRole(company, UserRole.CompanyAdministrator);
        /*
        List<CompanyAdministrator> admins = users.stream()
                .filter(user -> user instanceof CompanyAdministrator)
                .map(user -> (CompanyAdministrator) user)
                .collect(Collectors.toList());
         */
        return new Company(name, address, rating, availableEquipment, admins);
    }
    public List<Company> findByIdIn(List<Long> ids) { return companyRepository.findByIdIn(ids); }
    public Optional<Company> findById(Long id) { return companyRepository.findById(id); }
}