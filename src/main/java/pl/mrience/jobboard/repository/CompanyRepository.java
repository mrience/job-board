package pl.mrience.jobboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mrience.jobboard.domain.Company;
import pl.mrience.jobboard.domain.JobAd;

public interface CompanyRepository extends JpaRepository <Company, Integer> {
}
