package pl.mrience.jobboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "keywords")
@Setter
@Getter
@NoArgsConstructor
public class Keyword {

    @Id
    @NaturalId
    @Size(max = 15)
    private String keyword;

    @ManyToMany(mappedBy = "keywords")
    private Set<JobAd> jobAds;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Keyword)) return false;
        Keyword keyword1 = (Keyword) o;
        return getKeyword().equals(keyword1.getKeyword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKeyword());
    }
}
