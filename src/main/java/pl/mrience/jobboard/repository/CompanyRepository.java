package pl.mrience.jobboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mrience.jobboard.domain.Company;
import pl.mrience.jobboard.domain.JobAd;

@Repository
public interface CompanyRepository extends JpaRepository <Company, Integer> {
}
