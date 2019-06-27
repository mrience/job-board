package pl.mrience.jobboard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "keywords")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Keyword {

    @Id
    @Size(max = 15)
    private String keyword;

    @ManyToMany(mappedBy = "keywords")
    private Set<JobAd> jobAds;
}
