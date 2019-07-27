package pl.mrience.jobboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mrience.jobboard.domain.Company;
import pl.mrience.jobboard.domain.JobAd;

import javax.transaction.Transactional;

@Repository
public interface CompanyRepository extends JpaRepository <Company, Integer> {

    @Query("SELECT CASE WHEN count(c) > 0 THEN true ELSE false END FROM Company c WHERE nip = c.nip")
    public boolean existsByNip (String nip);

    @Transactional
    @Modifying
    @Query("DELETE FROM Company c WHERE nip = c.nip")
    public void deleteByNip (String nip);
}
