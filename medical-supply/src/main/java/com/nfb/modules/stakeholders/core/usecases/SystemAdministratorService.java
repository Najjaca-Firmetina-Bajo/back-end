package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.modules.stakeholders.core.domain.user.SystemAdministrator;
import com.nfb.modules.stakeholders.core.repositories.SystemAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemAdministratorService {

    private final SystemAdministratorRepository systemAdministratorRepository;
    private final UserService userService;

    @Autowired
    public SystemAdministratorService(SystemAdministratorRepository systemAdministratorRepository, UserService userService) {
        this.systemAdministratorRepository = systemAdministratorRepository;
        this.userService = userService;
    }

    public SystemAdministrator register(SystemAdministrator systemAdministrator) {
        SystemAdministrator sa = systemAdministratorRepository.save(systemAdministrator);
        userService.activateUser(sa.getId());
        return sa;
    }

    public void updatePasswordChanged(Long adminId) { systemAdministratorRepository.updatePasswordChanged(adminId); }
}
