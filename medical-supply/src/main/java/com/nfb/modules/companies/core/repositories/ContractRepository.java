package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.contract.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
