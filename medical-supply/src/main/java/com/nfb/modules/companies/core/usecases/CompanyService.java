package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService   {
    private final CompanyRepository companyRepository;
    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company register(Company company) { return companyRepository.save(company); }
    public List<Company> getAll() { return companyRepository.findAll(); }
}
