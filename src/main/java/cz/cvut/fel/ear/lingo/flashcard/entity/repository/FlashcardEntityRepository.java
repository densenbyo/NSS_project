package cz.cvut.fel.ear.lingo.flashcard.entity.repository;

import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlashcardEntityRepository extends JpaRepository<FlashcardEntity, Long> {

    Optional<FlashcardEntity> findFlashcardEntityByIdAndRemovedIsFalse(Long id);

    List<FlashcardEntity> findFlashcardEntitiesByRemovedIsFalse();

    List<FlashcardEntity> findFlashcardEntitiesByWordAndRemovedIsFalse(String word);

}
