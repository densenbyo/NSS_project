package cz.cvut.fel.ear.lingo.flashcard.mapper;

import cz.cvut.fel.ear.lingo.content.mapper.AbstractContent2ContentModelMapper;
import cz.cvut.fel.ear.lingo.content.model.ContentModel;
import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardModel;
import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardProgressModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Flashcard2FlashcardModelMapper {

    private final AbstractContent2ContentModelMapper abstractContent2ContentModelMapper;
    private final FlashcardProgress2FlashcardProgressModelMapper flashcardProgress2FlashcardProgressModelMapper;

    public FlashcardModel toFlashcardModel(Flashcard flashcard) {
        if (flashcard == null) return null;
        List<ContentModel> contentModels = abstractContent2ContentModelMapper
                .toContentModelList(flashcard.getAbstractContents());
        List<FlashcardProgressModel> progresses = flashcardProgress2FlashcardProgressModelMapper
                .toFlashcardProgressModelList(flashcard.getFlashcardProgresses());
        return FlashcardModel.builder()
                .id(flashcard.getId())
                .isRemoved(flashcard.isRemoved())
                .word(flashcard.getWord())
                .translation(flashcard.getTranslation())
                .contentModels(contentModels)
                .flashcardProgressModels(progresses)
                .creatorId(flashcard.getCreatorId())
                .build();
    }

    public List<FlashcardModel> toFlashcardModelList(List<Flashcard> flashcards) {
        List<FlashcardModel> flashcardsResponse = new ArrayList<>(flashcards.size());
        for (Flashcard flashcard : flashcards) {
            flashcardsResponse.add(toFlashcardModel(flashcard));
        }
        return flashcardsResponse;
    }
}
