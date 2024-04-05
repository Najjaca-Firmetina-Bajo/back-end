package com.nfb.modules.companies.core.repositories;

import com.nfb.modules.companies.core.domain.company.CompanyRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRatingRepository extends JpaRepository<CompanyRating,Long> {

    @Query("select c from CompanyRating c where c.company.id = :companyId")
    public List<CompanyRating> findCompaniesRatings(Long companyId);
}
