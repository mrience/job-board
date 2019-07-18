package pl.mrience.jobboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "job_ads")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_ad_id")
    @Setter(AccessLevel.NONE)
    private Integer jobAdId;

    @NotBlank
    @Size(max = 30)
    private String title;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "start_date")
    private Date startDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expire_date")
    private Date expireDate;

    @NotBlank
    private String content;

    @Size(max = 255)
    @Column(name = "link_to_form")
    private String linkToForm;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "nip")
    private Company company;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "job_ads_addresses",
            joinColumns = { @JoinColumn(name = "job_ad_id") },
            inverseJoinColumns = { @JoinColumn(name = "address_id") }
    )
    private Set<Address> addresses = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY) //check it!!!
    @JoinTable(
            name = "keywords_jobads",
            joinColumns = { @JoinColumn(name = "job_ad_id") },
            inverseJoinColumns = { @JoinColumn(name = "keyword") }
    )
    private Set <Keyword> keywords = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobAd)) return false;
        JobAd jobAd = (JobAd) o;
        return getJobAdId().equals(jobAd.getJobAdId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public static class JobAdBuilder {
        private String title;
        private Date startDate;
        private Date expireDate;
        private String content;
        private String linkToForm;
        private Company company;
        private Set<Address> addresses = new HashSet<>();
        private Set <Keyword> keywords = new HashSet<>();

        public JobAd build() {
            JobAd jobAd = new JobAd();
            jobAd.setTitle(this.title);
            jobAd.setStartDate(this.startDate);
            jobAd.setExpireDate(this.expireDate);
            jobAd.setContent(this.content);
            jobAd.setLinkToForm(this.linkToForm);
            jobAd.setCompany(this.company);
            jobAd.getCompany().getJobAds().add(jobAd);
            jobAd.setAddresses(this.addresses);
            addresses.forEach(a -> a.getJobAds().add(jobAd));
            jobAd.setKeywords(this.keywords);
            keywords.forEach(k -> k.getJobAds().add(jobAd));

            return jobAd;
        }

        public JobAdBuilder title(String title) {
            this.title = title;
            return this;
        }

        public JobAdBuilder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public JobAdBuilder expireDate(Date expireDate) {
            this.expireDate = expireDate;
            return this;
        }

        public JobAdBuilder content(String content) {
            this.content = content;
            return this;
        }

        public JobAdBuilder linkToForm(String link) {
            this.linkToForm = link;
            return this;
        }

        public JobAdBuilder company(Company company) {
            this.company = company;
            return this;
        }

        public JobAdBuilder addresses(Set<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public JobAdBuilder keywords(Set<Keyword> keywords) {
            this.keywords = keywords;
            return this;
        }
    }
}
