package pl.mrience.jobboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mrience.jobboard.domain.Address;

public interface AddressRepository extends JpaRepository <Address, Integer> {
}
