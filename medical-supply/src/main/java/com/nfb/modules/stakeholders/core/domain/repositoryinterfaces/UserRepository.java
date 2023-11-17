package com.nfb.modules.stakeholders.core.domain.repositoryinterfaces;

import com.nfb.buildingblocks.infrastrucure.GenericRepository;
import com.nfb.modules.stakeholders.core.domain.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User> {

}
