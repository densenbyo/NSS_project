package cz.cvut.fel.ear.lingo.content.entity.repository;

import cz.cvut.fel.ear.lingo.content.entity.AbstractContentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentEntityRepository extends CrudRepository<AbstractContentEntity, Long> {
}
