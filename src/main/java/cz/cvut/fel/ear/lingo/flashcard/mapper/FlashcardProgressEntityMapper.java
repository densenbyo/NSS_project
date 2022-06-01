package cz.cvut.fel.ear.lingo.flashcard.mapper;

import cz.cvut.fel.ear.lingo.flashcard.domain.FlashcardProgress;
import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardProgressEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlashcardProgressEntityMapper {

    public FlashcardProgress toFlashcardProgress(FlashcardProgressEntity flashcardProgressEntity) {
        if (flashcardProgressEntity == null) return null;
        FlashcardProgress progress = new FlashcardProgress();
        progress.setId(flashcardProgressEntity.getId());
        progress.setProgressDegree(flashcardProgressEntity.getProgressDegree());
        progress.setFlashcardId(flashcardProgressEntity.getFlashcardEntity().getId());
        return progress;
    }

    public List<FlashcardProgress> toFlashcardProgressList(List<FlashcardProgressEntity> flashcardProgressEntities) {
        List<FlashcardProgress> flashcardProgresses = new ArrayList<>(flashcardProgressEntities.size());
        for (FlashcardProgressEntity entity : flashcardProgressEntities) {
            flashcardProgresses.add(toFlashcardProgress(entity));
        }
        return flashcardProgresses;
    }
}
