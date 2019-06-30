package pl.mrience.jobboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addresses")
@Setter
@Getter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private Integer addressId;

    @NotBlank
    @Size(max = 20)
    private String city;

    @Size(max = 30)
    private String street;

    @Size(max = 20)
    private String state;

    @Size(max = 6, min = 6)
    @Column(name = "postal_code")
    private String postalCode;

    @ManyToMany(mappedBy = "addresses")
    private Set<JobAd> jobAds;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getAddressId(), address.getAddressId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
