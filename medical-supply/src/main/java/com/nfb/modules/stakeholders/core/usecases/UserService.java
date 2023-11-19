package com.nfb.modules.stakeholders.core.usecases;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.nfb.modules.stakeholders.core.domain.user.User;
import com.nfb.modules.stakeholders.core.domain.user.UserRole;
import com.nfb.modules.stakeholders.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private EmailService emailService = new EmailService();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public User register(User user) {

        try {
            this.emailService.sendRegistrationEmail("medicinskaopremagas@outlook.com");
        } catch (MailjetSocketTimeoutException e) {
            throw new RuntimeException(e);
        } catch (MailjetException e) {
            throw new RuntimeException(e);
        }

        var ret = userRepository.save(user);
        return ret;
    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
