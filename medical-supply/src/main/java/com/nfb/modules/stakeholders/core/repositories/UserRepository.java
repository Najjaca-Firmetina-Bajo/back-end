package com.nfb.modules.stakeholders.core.repositories;

import com.nfb.modules.stakeholders.core.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(@Param("id") long id);
}
