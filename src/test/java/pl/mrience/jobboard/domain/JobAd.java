package pl.mrience.jobboard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "job_ads")
@Getter
@Setter
@NoArgsConstructor
public class JobAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_ad_id")
    private Integer jobAdId;

    @NotBlank
    @Size(max = 30)
    private String title;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "start_date")
    private Date startDate;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expire_date")
    private Date expireDate;

    @NotBlank
    private String content;

    @Size(max = 255)
    private String linkToForm;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "nip")
    private Company company;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "notices_addresses",
            joinColumns = { @JoinColumn(name = "job_ad_id") },
            inverseJoinColumns = { @JoinColumn(name = "address_id") }
    )
    private Set<Address> addresses;

    @ManyToMany//(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}) //check it!!!
    @JoinTable(
            name = "keywords_jobads",
            joinColumns = { @JoinColumn(name = "job_ad_id") },
            inverseJoinColumns = { @JoinColumn(name = "keyword") }
    )
    private Set <Keyword> keywords;

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
}
