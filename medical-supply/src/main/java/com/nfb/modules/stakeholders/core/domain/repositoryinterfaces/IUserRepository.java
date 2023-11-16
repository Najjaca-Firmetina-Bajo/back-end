package com.nfb.modules.stakeholders.core.domain.repositoryinterfaces;

import com.nfb.modules.stakeholders.core.domain.user.User;

import java.util.List;

public interface IUserRepository {
    boolean exists(String username);
    User create(User user);
    List<User> getAll();
    User update(User user);
}
