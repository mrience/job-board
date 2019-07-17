package pl.mrience.jobboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mrience.jobboard.domain.Keyword;

@Repository
public interface KeywordRepository extends JpaRepository <Keyword, String> {
}
