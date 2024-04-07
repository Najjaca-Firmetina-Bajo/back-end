package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
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

    public RegisteredUser getRegisteredUser(long id) {
        return registeredUserRepository.findById(id);
    }

    public int givePenalPoints(long userId) {
        RegisteredUser ru = registeredUserRepository.findById(userId);
        int penalPoints = ru.getPenalPoints() + 2;
        registeredUserRepository.updatePenalPoints(penalPoints, userId);
        return penalPoints;
    }

    public void updateRegisteredUser(RegisteredUser registeredUser){
        registeredUserRepository.save(registeredUser);
    }

    public RegisteredUser removeUsersPenalPoints(long id) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            registeredUserRepository.removeUsersPenalPoints(id);
            return registeredUserRepository.findById(id);
        }
        return registeredUserRepository.findById(id);
    }
}
