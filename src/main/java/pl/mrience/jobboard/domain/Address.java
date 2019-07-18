package pl.mrience.jobboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addresses")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
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
    private Set<JobAd> jobAds = new HashSet<>();

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

    public static class AddressBuilder {
        private String city;
        private String street;
        private String state;
        private String postalCode;

        public Address build()  {
            Address address = new Address();
            address.setCity(this.city);
            address.setStreet(this.street);
            address.setState(this.state);
            address.setPostalCode(this.postalCode);

            return address;
        }

        public AddressBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder state(String state) {
            this.state = state;
            return this;
        }

        public AddressBuilder postalCode( String postalCode) {
            this.postalCode = postalCode;
            return this;
        }
    }
}
