package cz.cvut.fel.ear.lingo.repo.entity.repository;

import cz.cvut.fel.ear.lingo.repo.entity.RepoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoEntityRepository extends JpaRepository<RepoEntity, Long> {
}
