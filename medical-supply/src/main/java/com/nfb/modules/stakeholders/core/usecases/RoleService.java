package com.nfb.modules.stakeholders.core.usecases;

import java.util.List;

import com.nfb.modules.stakeholders.core.domain.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nfb.modules.stakeholders.core.repositories.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public Role findById(Long id) {
        Role auth = this.roleRepository.getOne(id);
        return auth;
    }


    public List<Role> findByName(String name) {
        List<Role> roles = this.roleRepository.findByName(name);
        return roles;
    }


}
