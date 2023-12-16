package com.nfb.modules.stakeholders.core.repositories;

import java.util.List;

import com.nfb.modules.stakeholders.core.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
}

