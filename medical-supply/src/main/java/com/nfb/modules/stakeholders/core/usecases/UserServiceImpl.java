package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.buildingblocks.core.usecases.impl.ServiceGenericImpl;
import com.nfb.modules.stakeholders.API.services.UserService;
import com.nfb.modules.stakeholders.core.domain.user.User;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceGenericImpl<User> implements UserService {
}