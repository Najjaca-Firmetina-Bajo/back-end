package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.API.dtos.CompanyUpdateDto;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.usecases.CompanyAdministratorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    public Company prepareCompanyModel(Long companyId, String name, String address, double rating)
    {
        Company company = companyRepository.findById(companyId).orElse(null);
        List<CompanyAdministrator> admins = companyAdministratorService.findByCompany(company);
        return new Company(name, address, rating, admins);
    }
    public List<Company> findByIdIn(List<Long> ids) { return companyRepository.findByIdIn(ids); }
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    public Company findOneById(Long id) {
        return companyRepository.findOneById(id);
    }

    @Transactional
    public Company addAdministratorToCompany(long companyId, long administratorId)
    {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Company not found."));
        CompanyAdministrator administrator = companyAdministratorService.findById(administratorId).orElseThrow(() -> new EntityNotFoundException("Company administrator not found."));

        List<CompanyAdministrator> administrators = company.getAdministrators();
        administrators.add(administrator);
        companyRepository.addAdministratorToCompany(companyId, administrators);

        return companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Company not found."));
    }

    public Company findByName(String name) { return companyRepository.findByName(name); }

    public Company update(long id, CompanyUpdateDto companyDto){
        var company = companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Company not found."));

        company.setName(companyDto.getName());
        company.setAddress(companyDto.getAddress());
        company.setAverageRating(companyDto.getAvgRating());
        company.setAdministrators(companyDto.getAdministrators());

        return companyRepository.save(company);
    }
}
