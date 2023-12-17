package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.repositories.RegistredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistredUserService {
    private final RegistredUserRepository registredUserRepository;

    @Autowired
    public RegistredUserService(RegistredUserRepository registredUserRepository) {
        this.registredUserRepository = registredUserRepository;
    }

    public List<RegisteredUser> getAll() { return registredUserRepository.findAll(); }
}
