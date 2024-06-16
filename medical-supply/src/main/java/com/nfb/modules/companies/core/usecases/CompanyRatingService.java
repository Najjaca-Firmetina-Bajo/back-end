package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.company.CompanyRating;
import com.nfb.modules.companies.core.repositories.CompanyRatingRepository;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyRatingService {
    private final CompanyRatingRepository companyRatingRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyRatingService(CompanyRatingRepository companyRatingRepository, CompanyRepository companyRepository){
        this.companyRatingRepository = companyRatingRepository;
        this.companyRepository = companyRepository;
    }

    //@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public CompanyRating save(CompanyRating rating){
        Company company = companyRepository.getById(rating.getCompany().getId());

        companyRatingRepository.save(rating);

        List<CompanyRating> companyRatingList = companyRatingRepository.findCompaniesRatings(company.getId());

        company.setAverageRating(calculateAverageRating(companyRatingList));
        companyRepository.save(company);
        return rating;
    }

    private double calculateAverageRating(List<CompanyRating> companyRatingList) {
        int count = 0;
        double avg = 0;
        for (CompanyRating cr :
                companyRatingList) {
            avg += cr.getRating();
            count++;
        }
        return avg = avg / count;
    }

    public CompanyRating findUsersRatingForCompany(long companyId, long userId){
        return companyRatingRepository.findUsersRatingForCompany(companyId,userId);
    }
}
