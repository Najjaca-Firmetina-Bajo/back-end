package com.nfb.modules.stakeholders.core.repositories;

import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
    RegisteredUser findByUsername(String username);
    RegisteredUser findById(long id);

    @Modifying
    @Query("UPDATE RegisteredUser ru SET ru.penalPoints = :penalPoints WHERE ru.id = :userId")
    @Transactional
    void updatePenalPoints(@Param("penalPoints") int penalPoints, @Param("userId") Long userId);

    @Override
    <S extends RegisteredUser> S save(S registeredUser);

    @Modifying
    @Query("UPDATE RegisteredUser ru SET ru.penalPoints = 0 WHERE ru.id = :id")
    @Transactional
    void removeUsersPenalPoints(long id);

    @Query("SELECT ru FROM RegisteredUser ru " +
            "JOIN QRCode qr ON ru = qr.registeredUser " +
            "JOIN Appointment ap ON qr.appointment = ap " +
            "JOIN CompanyAdministrator ca ON ap.companyAdministrator = ca " +
            "WHERE ca.company.id = :companyId")
    List<RegisteredUser> findAllRegisteredUsersWithQRCodeByCompanyId(@Param("companyId") Long companyId);
}
