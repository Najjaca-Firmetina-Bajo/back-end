package com.nfb.modules.companies.API.serviceinterfaces;

import com.nfb.modules.companies.core.domain.company.Company;

public interface ICompanyService {
    public Company register();
    public Company edit();
}
