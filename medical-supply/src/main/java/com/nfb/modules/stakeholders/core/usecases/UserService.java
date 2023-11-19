package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.modules.companies.core.domain.company.Company;
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

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        //userRepository.save(new User("ivan","mikica", UserRole.RegisteredUser));
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public User register(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
