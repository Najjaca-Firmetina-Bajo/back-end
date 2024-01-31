package com.nfb.modules.stakeholders.core.repositories;

import com.nfb.modules.stakeholders.core.domain.user.SystemAdministrator;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemAdministratorRepository extends JpaRepository<SystemAdministrator, Long> {
    @Modifying
    @Query("UPDATE SystemAdministrator sa SET sa.passwordChanged = true WHERE sa.id = :adminId")
    @Transactional
    void updatePasswordChanged(@Param("adminId") Long adminId);
}
