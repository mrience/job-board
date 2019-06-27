package pl.mrience.jobboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "keywords")
@Setter
@Getter
@NoArgsConstructor
public class Keyword {

    @Id
    @Size(max = 15)
    private String keyword;
}
