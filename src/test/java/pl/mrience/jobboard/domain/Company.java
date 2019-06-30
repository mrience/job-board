package pl.mrience.jobboard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {

    @Id
    @NaturalId
    private Integer nip;

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
    private Set <JobAd> jobAds;


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
}
