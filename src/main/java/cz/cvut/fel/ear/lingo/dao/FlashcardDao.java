package cz.cvut.fel.ear.lingo.dao;

import cz.cvut.fel.ear.lingo.flashcard_service.entity.FlashcardEntity;
import cz.cvut.fel.ear.lingo.model.domain.FlashcardDomain;
import cz.cvut.fel.ear.lingo.dao.mapper.FlashcardEntity2FlashCardDomainMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class FlashcardDao extends BaseDao<FlashcardDomain> {


    protected FlashcardDao() {
        super(FlashcardDomain.class);
    }

    @Override
    public List<FlashcardDomain> findAll() {
        try {
            var flashcardEntity = em.createQuery("SELECT f FROM FlashcardEntity f WHERE NOT f.isRemoved", FlashcardEntity.class)
                    .getResultList();
            FlashcardEntity2FlashCardDomainMapper mapper = new FlashcardEntity2FlashCardDomainMapper();
            var flashCardListDomain = mapper.toFlashcardDomainList(flashcardEntity);
            return flashCardListDomain;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<FlashcardEntity> findByWord(String word) {
        try {
            return (List<FlashcardEntity>) em.createNamedQuery("findByWord")
                    .setParameter(1, word)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<FlashcardEntity> findCardsInPublicDecks(){
        try {
            return em.createQuery("SELECT f FROM FlashcardEntity f JOIN FlashcardDeckEntity fd WHERE f MEMBER OF fd.flashcards AND fd.isPublic = true ", FlashcardEntity.class)
                    .getResultList();
        } catch (NoResultException e){
            return null;
        }
    }
}