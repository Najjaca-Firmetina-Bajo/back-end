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

    @Modifying
    @Query("UPDATE User u SET u.city = :city, u.country = :country, u.username = :username, u.name = :name, u.phoneNumber = :phoneNumber, u.surname = :surname WHERE u.id = :id")
    @Transactional
    void updateUserDetails(@Param("id") long id, @Param("city") String city, @Param("country") String country, @Param("username") String username, @Param("name") String name, @Param("phoneNumber") String phoneNumber, @Param("surname") String surname);

}

