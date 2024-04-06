package com.nfb.modules.companies.API.dtos;

import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import jakarta.persistence.*;

import java.util.List;

public class CompanyRatingDto {
    private long rating;
    private List<String> ratingReasons;
    private String ratingDescription;
    private long companyId;
    private long userId;

    public CompanyRatingDto(long rating, List<String> ratingReasons, String ratingDescription, long companyId, long userId) {
        this.rating = rating;
        this.ratingReasons = ratingReasons;
        this.ratingDescription = ratingDescription;
        this.companyId = companyId;
        this.userId = userId;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public List<String> getRatingReasons() {
        return ratingReasons;
    }

    public void setRatingReasons(List<String> ratingReasons) {
        this.ratingReasons = ratingReasons;
    }

    public String getRatingDescription() {
        return ratingDescription;
    }

    public void setRatingDescription(String ratingDescription) {
        this.ratingDescription = ratingDescription;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
