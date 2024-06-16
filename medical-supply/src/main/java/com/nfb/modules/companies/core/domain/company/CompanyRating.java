package com.nfb.modules.companies.core.domain.company;

import com.nfb.modules.stakeholders.core.domain.user.RegisteredUser;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "company_ratings")
public class CompanyRating {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private long rating;
    @ElementCollection
    private List<String> ratingReasons;
    @Column(nullable = true)
    private String ratingDescription;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToOne
    @JoinColumn(name = "registered_user_id")
    private RegisteredUser user;

    @Version
    private Long version;

    public CompanyRating(long rating, List<String> ratingReasons, String ratingDescription, Company company, RegisteredUser user) {
        this.rating = rating;
        this.ratingReasons = ratingReasons;
        this.ratingDescription = ratingDescription;
        this.company = company;
        this.user = user;
    }

    public CompanyRating() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public RegisteredUser getUser() {
        return user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
