package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.modules.companies.core.domain.company.Company;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import com.nfb.modules.stakeholders.core.domain.user.User;
import com.nfb.modules.stakeholders.core.domain.user.Role;
import com.nfb.modules.stakeholders.core.repositories.CompanyAdministratorRepository;
import com.nfb.modules.stakeholders.core.repositories.RegisteredUserRepository;
import com.nfb.modules.stakeholders.core.repositories.UserRepository;
import javassist.NotFoundException;
import org.modelmapper.internal.bytebuddy.build.BuildLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final CompanyAdministratorRepository companyAdministratorRepository;
    private final EmailSender emailSender;

    @Autowired
    public UserService(UserRepository userRepository,RegisteredUserRepository registeredUserRepository,CompanyAdministratorRepository companyAdministratorRepository,EmailSender emailSender) {
        this.userRepository = userRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.companyAdministratorRepository = companyAdministratorRepository;
        this.emailSender = emailSender;
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public RegisteredUser register(RegisteredUser registeredUser) {

        var ret = registeredUserRepository.save(registeredUser);
        emailSender.sendHtmlEmail(registeredUser,"User verification");

        return ret;
    }

    public CompanyAdministrator register(CompanyAdministrator companyAdministrator) {

        var ret = companyAdministratorRepository.save(companyAdministrator);
        emailSender.sendHtmlEmail(companyAdministrator,"User verification");


        return ret;
    }

    public User activateUser(long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(true);
            return userRepository.save(user);
        } else
        {
            return null;
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String email) {
        return userRepository.findByUsername(email);
    }

    public void updatePassword(String password, long id) { userRepository.updatePassword(password, id); }
}
