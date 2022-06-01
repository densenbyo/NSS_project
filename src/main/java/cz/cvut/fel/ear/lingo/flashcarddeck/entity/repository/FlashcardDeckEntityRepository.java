package cz.cvut.fel.ear.lingo.flashcarddeck.entity.repository;

import cz.cvut.fel.ear.lingo.flashcarddeck.entity.FlashcardDeckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlashcardDeckEntityRepository extends JpaRepository<FlashcardDeckEntity, Long> {

    List<FlashcardDeckEntity> findFlashcardDeckEntitiesByIsRemovedIsFalse();

    Optional<FlashcardDeckEntity> findFlashcardDeckEntityByIdAndIsRemovedIsFalse(Long id);

}
