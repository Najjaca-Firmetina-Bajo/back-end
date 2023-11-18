package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService   {
    private final CompanyRepository companyRepository;
    private final EquipmentService equipmentService;
    @Autowired
    public CompanyService(CompanyRepository companyRepository, EquipmentService equipmentService) {
        this.companyRepository = companyRepository;
        this.equipmentService = equipmentService;
    }

    public Company register(Company company) { return companyRepository.save(company); }
    public List<Company> getAll() { return companyRepository.findAll(); }
    public Company prepareCompanyModel(String name, String address, double rating, List<Long> availableEquipmentIds) {
        List<Equipment> availableEquipment = equipmentService.findByIdIn(availableEquipmentIds);
        return new Company(name, address, rating, availableEquipment);
    }
    public List<Company> findByIdIn(List<Long> ids) { return companyRepository.findByIdIn(ids); }
}
