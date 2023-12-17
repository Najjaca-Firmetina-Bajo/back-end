package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.modules.stakeholders.core.domain.user.SystemAdministrator;
import com.nfb.modules.stakeholders.core.repositories.SystemAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemAdministratorService {

    private final SystemAdministratorRepository systemAdministratorRepository;

    @Autowired
    public SystemAdministratorService(SystemAdministratorRepository systemAdministratorRepository) {
        this.systemAdministratorRepository = systemAdministratorRepository;
    }

    public SystemAdministrator register(SystemAdministrator systemAdministrator) { return systemAdministratorRepository.save(systemAdministrator); }

    public void updatePasswordChanged(Long adminId) { systemAdministratorRepository.updatePasswordChanged(adminId); }
}
