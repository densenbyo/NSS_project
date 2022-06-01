package cz.cvut.fel.ear.lingo.flashcarddeck.mapper;

import cz.cvut.fel.ear.lingo.flashcard.mapper.Flashcard2FlashcardEntityMapper;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import cz.cvut.fel.ear.lingo.flashcarddeck.entity.FlashcardDeckEntity;
import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FlashcardDeck2FlashcardDeckEntityMapper {

    private final Flashcard2FlashcardEntityMapper flashcard2FlashcardEntityMapper;

    public FlashcardDeckEntity toFlashcardDeckEntity(FlashcardDeck flashcardDeck, UserEntity userEntity) {
        if (flashcardDeck == null) return null;
        var flashcardEntities = flashcard2FlashcardEntityMapper
                .toFlashcardEntityList(flashcardDeck.getFlashcards(), userEntity);
        FlashcardDeckEntity flashcardDeckEntity = new FlashcardDeckEntity();
        flashcardDeckEntity.setId(flashcardDeck.getId());
        flashcardDeckEntity.setName(flashcardDeck.getName());
        flashcardDeckEntity.setDescription(flashcardDeck.getDescription());
        flashcardDeckEntity.setIsPublic(flashcardDeck.getIsPublic());
        flashcardDeckEntity.setIsRemoved(flashcardDeck.getIsRemoved());
        flashcardDeckEntity.setCreator(userEntity);
        flashcardDeckEntity.setFlashcards(flashcardEntities);
        return flashcardDeckEntity;
    }

    public List<FlashcardDeckEntity> toFlashcardDeckEntitiesList(List<FlashcardDeck> flashcardDecks,UserEntity userEntity) {
        List<FlashcardDeckEntity> flashcardDeckEntities = new ArrayList<>(flashcardDecks.size());
        for (FlashcardDeck flashcardDeck : flashcardDecks) {
            flashcardDeckEntities.add(toFlashcardDeckEntity(flashcardDeck, userEntity));
        }
        return flashcardDeckEntities;
    }
}
