package com.nfb.modules.companies.core.usecases;

import com.nfb.buildingblocks.core.usecases.BaseService;
import com.nfb.modules.companies.API.dtos.CompanyDto;
import com.nfb.modules.companies.API.serviceinterfaces.ICompanyService;
import com.nfb.modules.companies.core.domain.company.Company;
import org.modelmapper.ModelMapper;

public class CompanyService extends BaseService<CompanyDto, Company> implements ICompanyService  {
    protected CompanyService(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public Company register() {
        return null;
    }

    @Override
    public Company edit() {
        return null;
    }

    @Override
    protected Class<Company> getDomainClass() {
        return null;
    }

    @Override
    protected Class<CompanyDto> getDtoClass() {
        return null;
    }
}
