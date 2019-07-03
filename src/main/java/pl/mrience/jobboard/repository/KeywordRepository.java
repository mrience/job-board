package pl.mrience.jobboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mrience.jobboard.domain.Keyword;

public interface KeywordRepository extends JpaRepository <Keyword, String> {
}
