package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByIdIn(List<Long> ids);

    Company findByName(String name);

    List<Company> findByNameIgnoreCaseOrAddressContainingIgnoreCase(String name, String place);

    @Query("SELECT c FROM Company c WHERE :minAverageRating IS NULL OR c.averageRating < :minAverageRating")
    List<Company> findCompaniesByAverageRating(@Param("minAverageRating") double minAverageRating);

    @Query("SELECT c FROM Company c, CompanyEquipment ce WHERE c.id = ce.company.id AND ce.quantity <= :equipmentCount")
    List<Company> filterByEquipmentCount(@Param("equipmentCount") int equipmentCount);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value ="1000")})
    @Query("SELECT c from Company c where c.id = :id")
    Company findOneById(@Param("id")Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Company c SET c.administrators = :administrators WHERE c.id = :companyId")
    void addAdministratorToCompany(@Param("companyId") long companyId, @Param("administrators") List<CompanyAdministrator> administrators);

    @Query("SELECT c.administrators from Company c where c.id = :id")
    List<CompanyAdministrator> getCompanyAdministrators(long id);

    @Query("select c from Company c order by c.averageRating desc ")
    List<Company> sortCompaniesByRatingDesc();

    @Query("select c from Company c order by c.averageRating asc ")
    List<Company> sortCompaniesByRatingAsc();

    @Query("select c from Company c order by c.name desc")
    List<Company> sortCompaniesByNameDesc();

    @Query("select c from Company c order by c.name asc")
    List<Company> sortCompaniesByNameAsc();

    @Query("select c from Company c order by c.address desc")
    List<Company> sortCompaniesByAddressDesc();

    @Query("select c from Company c order by c.address desc")
    List<Company> sortCompaniesByAddressAsc();

    @Query("SELECT c FROM Company c JOIN CompanyAdministrator ca ON c.id = ca.company.id WHERE ca.id = :adminId")
    Company findByAdminId(@Param("adminId") Long adminId);
}
