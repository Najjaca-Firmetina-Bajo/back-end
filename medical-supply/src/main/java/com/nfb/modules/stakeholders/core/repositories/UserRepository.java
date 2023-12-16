package com.nfb.modules.stakeholders.core.repositories;


import com.nfb.modules.stakeholders.core.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

