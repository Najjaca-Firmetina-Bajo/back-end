package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredUserService {
    private final RegisteredUserRepository registeredUserRepository;

    @Autowired
    public RegisteredUserService(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
    }

    public RegisteredUser getByUsername(String username) {
        return registeredUserRepository.findByUsername(username);
    }

    public RegisteredUser findById(long id) { return registeredUserRepository.findById(id); }

    public List<RegisteredUser> getAll() { return registeredUserRepository.findAll(); }

    public int givePenalPoints(long userId) {
        RegisteredUser ru = registeredUserRepository.findById(userId);
        int penalPoints = ru.getPenalPoints() + 2;
        registeredUserRepository.updatePenalPoints(penalPoints, userId);
        return penalPoints;
    }
}
