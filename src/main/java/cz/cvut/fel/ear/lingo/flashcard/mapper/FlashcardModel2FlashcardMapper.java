package cz.cvut.fel.ear.lingo.flashcard.mapper;

import cz.cvut.fel.ear.lingo.content.domain.AbstractContent;
import cz.cvut.fel.ear.lingo.content.mapper.ContentModel2AbstractContentMapper;
import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.domain.FlashcardProgress;
import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FlashcardModel2FlashcardMapper {

    private final ContentModel2AbstractContentMapper contentModel2AbstractContentMapper;
    private final FlashcardProgressModel2FlashcardProgressMapper flashcardProgressModel2FlashcardProgressMapper;

    public Flashcard toFlashcard(FlashcardModel flashcardModel) {
        if (flashcardModel == null) return null;
        List<AbstractContent> contentModels = contentModel2AbstractContentMapper
                .toAbstractContentList(flashcardModel.getContentModels());
        List<FlashcardProgress> flashcardProgresses = flashcardProgressModel2FlashcardProgressMapper
                .toFlashcardProgressList(flashcardModel.getFlashcardProgressModels());
        return Flashcard.builder()
                .id(flashcardModel.getId())
                .word(flashcardModel.getWord())
                .translation(flashcardModel.getTranslation())
                .isRemoved(flashcardModel.isRemoved())
                .creatorId(flashcardModel.getCreatorId())
                .abstractContents(contentModels)
                .flashcardProgresses(flashcardProgresses)
                .build();
    }

    public List<Flashcard> toFlashcardList(List<FlashcardModel> flashcardModels) {
        List<Flashcard> flashcards = new ArrayList<>(flashcardModels.size());
        for (FlashcardModel entity : flashcardModels) {
            flashcards.add(toFlashcard(entity));
        }
        return flashcards;
    }
}