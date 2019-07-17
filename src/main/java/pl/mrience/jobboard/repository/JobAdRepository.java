package pl.mrience.jobboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mrience.jobboard.domain.JobAd;

@Repository
public interface JobAdRepository extends JpaRepository <JobAd, Integer> {

    public JobAd getByCompany_Name(String companyName);
}
