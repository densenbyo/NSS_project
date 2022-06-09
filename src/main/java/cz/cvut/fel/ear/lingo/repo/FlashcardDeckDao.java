package cz.cvut.fel.ear.lingo.repo;

import cz.cvut.fel.ear.lingo.repo.dao.BaseDao;
import cz.cvut.fel.ear.lingo.model.FlashcardDeck;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Objects;

@Repository
public class FlashcardDeckDao extends BaseDao<FlashcardDeck> {

    protected FlashcardDeckDao() {
        super(FlashcardDeck.class);
    }

    public List<FlashcardDeck> findAll() {
        try {
            return em.createQuery(
                    "SELECT f FROM FlashcardDeck f WHERE NOT f.isRemoved", FlashcardDeck.class)
                    .getResultList();
        } catch (NoResultException e) {
            throw new NoResultException("Not Found");
        }
    }

    public FlashcardDeck getById(Long id){
        Objects.requireNonNull(id);
        try {
            return em.createQuery(
                            "SELECT f FROM FlashcardDeck f WHERE NOT f.isRemoved AND f.id = :id", FlashcardDeck.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }
}