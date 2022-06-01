package cz.cvut.fel.ear.lingo.flashcarddeck.mapper;

import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardModel2FlashcardMapper;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import cz.cvut.fel.ear.lingo.flashcarddeck.model.FlashcardDeckModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FlashcardDeckModel2FlashcardDeckMapper {

    private final FlashcardModel2FlashcardMapper flashcardModel2FlashcardMapper;

    public FlashcardDeck toFlashcardDeck(FlashcardDeckModel flashcardDeckModel) {
        if (flashcardDeckModel == null) return null;
        var flashcards = flashcardModel2FlashcardMapper
                .toFlashcardList(flashcardDeckModel.getFlashcardModels());
        return FlashcardDeck.builder()
                .id(flashcardDeckModel.getId())
                .isPublic(flashcardDeckModel.getIsPublic())
                .isRemoved(flashcardDeckModel.getIsRemoved())
                .name(flashcardDeckModel.getName())
                .description(flashcardDeckModel.getDescription())
                .creatorId(flashcardDeckModel.getCreatorId())
                .flashcards(flashcards)
                .build();
    }

    public List<FlashcardDeck> toFlashcardDeckList(List<FlashcardDeckModel> flashcardDeckModels) {
        List<FlashcardDeck> flashcardDecks = new ArrayList<>(flashcardDeckModels.size());
        for (FlashcardDeckModel model : flashcardDeckModels) {
            flashcardDecks.add(toFlashcardDeck(model));
        }
        return flashcardDecks;
    }
}
