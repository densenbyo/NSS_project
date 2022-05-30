package cz.cvut.fel.ear.lingo.dao;

import cz.cvut.fel.ear.lingo.content_service.entity.AbstractContentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentDao extends CrudRepository<AbstractContentEntity, Long> {

    Optional<AbstractContentEntity> findById(Long id);

    List<AbstractContentEntity> findAll();

    Optional<AbstractContentEntity> findByIdAndCreatorId(Long con_id, Long cre_id);
}
