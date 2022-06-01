package cz.cvut.fel.ear.lingo.flashcarddeck.mapper;

import cz.cvut.fel.ear.lingo.flashcard.mapper.Flashcard2FlashcardModelMapper;
import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardModel;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import cz.cvut.fel.ear.lingo.flashcarddeck.model.FlashcardDeckModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FlashcardDeck2FlashcardDeckModelMapper {

    private final Flashcard2FlashcardModelMapper flashcard2FlashcardModelMapper;

    public FlashcardDeckModel toFlashcardDeckModel(FlashcardDeck flashcardDeck) {
        if (flashcardDeck == null) return null;
        List<FlashcardModel> flashcardModels = flashcard2FlashcardModelMapper
                .toFlashcardModelList(flashcardDeck.getFlashcards());

        return FlashcardDeckModel.builder()
                .id(flashcardDeck.getId())
                .isPublic(flashcardDeck.getIsPublic())
                .isRemoved(flashcardDeck.getIsRemoved())
                .name(flashcardDeck.getName())
                .description(flashcardDeck.getDescription())
                .creatorId(flashcardDeck.getCreatorId())
                .flashcardModels(flashcardModels)
                .build();
    }

    public List<FlashcardDeckModel> toFlashcardDeckModelList(List<FlashcardDeck> flashcardDecks) {
        List<FlashcardDeckModel> flashcardModels = new ArrayList<>(flashcardDecks.size());
        for (FlashcardDeck flashcardDeck : flashcardDecks) {
            flashcardModels.add(toFlashcardDeckModel(flashcardDeck));
        }
        return flashcardModels;
    }
}
