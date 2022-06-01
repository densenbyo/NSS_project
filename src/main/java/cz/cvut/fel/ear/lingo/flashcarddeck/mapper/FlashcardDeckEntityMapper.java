package cz.cvut.fel.ear.lingo.flashcarddeck.mapper;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardEntityMapper;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import cz.cvut.fel.ear.lingo.flashcarddeck.entity.FlashcardDeckEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FlashcardDeckEntityMapper {

    private final FlashcardEntityMapper flashcardEntityMapper;

    public FlashcardDeck toFlashcardDeck(FlashcardDeckEntity flashcardDeckEntity) {
        if (flashcardDeckEntity == null) return null;
        List<Flashcard> flashcards = flashcardEntityMapper
                .toFlashcardList(flashcardDeckEntity.getFlashcards());

        return FlashcardDeck.builder()
                .id(flashcardDeckEntity.getId())
                .creatorId(flashcardDeckEntity.getCreator().getId())
                .isRemoved(flashcardDeckEntity.getIsRemoved())
                .isPublic(flashcardDeckEntity.getIsPublic())
                .name(flashcardDeckEntity.getName())
                .description(flashcardDeckEntity.getDescription())
                .flashcards(flashcards)
                .build();
    }

    public List<FlashcardDeck> toFlashcardDeckList(List<FlashcardDeckEntity> flashcardDeckEntities) {
        List<FlashcardDeck> flashcardDecks = new ArrayList<>(flashcardDeckEntities.size());
        for (FlashcardDeckEntity entity : flashcardDeckEntities) {
            flashcardDecks.add(toFlashcardDeck(entity));
        }
        return flashcardDecks;
    }
}
