package pl.mrience.jobboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Company {

    @Id
    @NaturalId
    @Setter(AccessLevel.PRIVATE)
    private String nip;

    @NotBlank
    @Size(max = 45)
    private String name;

    @NotBlank
    @Size(max = 20, min = 6)
    private String password;

    @NotBlank
    @Size(max = 45)
    private String email;

    @Size(max = 255)
    private String logo;

    @OneToMany(mappedBy = "company")
    private Set <JobAd> jobAds = new HashSet<>();

    public void addJobAd(JobAd jobAd) {
        this.jobAds.add(jobAd);
        jobAd.setCompany(this);
    }

    public void removeJobAd(JobAd jobAd) {
        this.jobAds.remove(jobAd);
        jobAd.setCompany(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return nip.equals(company.nip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nip);
    }

    public static class CompanyBuilder {
        private String nip;
        private String name;
        private String password;
        private String email;
        private String logo;
        private Set <JobAd> jobAds = new HashSet<>();

        public Company build() {
            Company company = new Company();
            company.setNip(this.nip);
            company.setName(this.name);
            company.setPassword(this.password);
            company.setEmail(this.email);
            company.setLogo(this.logo);
            return company;
        }

        public CompanyBuilder nip(String nip) {
            this.nip = nip;
            return this;
        }

        public CompanyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CompanyBuilder password(String password) {
            this.password = password;
            return this;
        }

        public CompanyBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CompanyBuilder logo(String logo) {
            this.logo = logo;
            return this;
        }
    }
}
