package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.contract.Contract;
import com.nfb.modules.companies.core.domain.contract.ContractStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findByUserIdAndCompanyIdAndStatus(Long userId, Long companyId, ContractStatus status);
    List<Contract> findAllByCompanyId(long companyId);
    @Query("SELECT c FROM Contract c WHERE c.pickupDate > :currentDateTime AND c.status = :status")
    List<Contract> findByPickupDateAfterAndStatus(LocalDateTime currentDateTime, ContractStatus status);
    List<Contract> findByPickupDateBetweenAndStatus(LocalDate startDate, LocalDate endDate, ContractStatus status);
}
