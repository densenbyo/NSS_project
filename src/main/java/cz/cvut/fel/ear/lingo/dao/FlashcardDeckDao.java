package cz.cvut.fel.ear.lingo.dao;

import cz.cvut.fel.ear.lingo.flashcarddeck_service.entity.FlashcardDeckEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Objects;

@Repository
public class FlashcardDeckDao extends BaseDao<FlashcardDeckEntity> {

    protected FlashcardDeckDao() {
        super(FlashcardDeckEntity.class);
    }

    public List<FlashcardDeckEntity> findAll() {
        try {
            return em.createQuery(
                    "SELECT f FROM FlashcardDeckEntity f WHERE NOT f.isRemoved", FlashcardDeckEntity.class)
                    .getResultList();
        } catch (NoResultException e) {
            throw new NoResultException("Not Found");
        }
    }

    public FlashcardDeckEntity getById(Long id){
        Objects.requireNonNull(id);
        try {
            return em.createQuery(
                            "SELECT f FROM FlashcardDeckEntity f WHERE NOT f.isRemoved AND f.id = :id", FlashcardDeckEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }
}