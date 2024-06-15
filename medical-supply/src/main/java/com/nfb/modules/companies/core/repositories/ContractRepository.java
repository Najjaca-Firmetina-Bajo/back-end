package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.contract.Contract;
import com.nfb.modules.companies.core.domain.contract.ContractStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findByUserIdAndCompanyIdAndStatus(Long userId, Long companyId, ContractStatus status);
    List<Contract> findAllByCompanyId(long companyId);
}
