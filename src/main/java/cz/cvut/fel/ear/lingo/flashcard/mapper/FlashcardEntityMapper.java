package cz.cvut.fel.ear.lingo.flashcard.mapper;

import cz.cvut.fel.ear.lingo.content.domain.AbstractContent;
import cz.cvut.fel.ear.lingo.content.mapper.AbstractContentEntityMapper;
import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.domain.FlashcardProgress;
import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FlashcardEntityMapper {

    private final AbstractContentEntityMapper abstractContentEntityMapper;
    private final FlashcardProgressEntityMapper flashcardProgressEntityMapper;

    public Flashcard toFlashcard(FlashcardEntity flashcardEntity) {
        if (flashcardEntity == null) return null;
        List<AbstractContent> contents = abstractContentEntityMapper
                .toAbstractContentList(flashcardEntity.getContents());
        List<FlashcardProgress> progresses = flashcardProgressEntityMapper
                .toFlashcardProgressList(flashcardEntity.getFlashcardProgresses());
        return Flashcard.builder()
                .id(flashcardEntity.getId())
                .translation(flashcardEntity.getTranslation())
                .word(flashcardEntity.getWord())
                .creatorId(flashcardEntity.getCreator().getId())
                .isRemoved(flashcardEntity.isRemoved())
                .abstractContents(contents)
                .flashcardProgresses(progresses)
                .build();
    }

    public List<Flashcard> toFlashcardList(List<FlashcardEntity> flashcardEntities) {
        List<Flashcard> flashcards = new ArrayList<>(flashcardEntities.size());
        for (FlashcardEntity entity : flashcardEntities) {
            flashcards.add(toFlashcard(entity));
        }
        return flashcards;
    }
}
