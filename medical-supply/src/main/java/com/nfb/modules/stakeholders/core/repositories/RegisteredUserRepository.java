package com.nfb.modules.stakeholders.core.repositories;

import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
}
