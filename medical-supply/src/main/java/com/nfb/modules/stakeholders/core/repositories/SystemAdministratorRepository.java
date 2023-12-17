package com.nfb.modules.stakeholders.core.repositories;

import com.nfb.modules.stakeholders.core.domain.user.SystemAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemAdministratorRepository extends JpaRepository<SystemAdministrator, Long> {
}
