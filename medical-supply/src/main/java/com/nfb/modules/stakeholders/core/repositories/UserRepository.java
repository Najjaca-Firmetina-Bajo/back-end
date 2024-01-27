package com.nfb.modules.stakeholders.core.repositories;


import com.nfb.modules.stakeholders.core.domain.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    @Transactional
    void updatePassword(@Param("password") String password, @Param("id") long id);
}

