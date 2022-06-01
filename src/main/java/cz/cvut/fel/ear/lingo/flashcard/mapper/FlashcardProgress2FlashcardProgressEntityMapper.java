package cz.cvut.fel.ear.lingo.flashcard.mapper;

import cz.cvut.fel.ear.lingo.flashcard.domain.FlashcardProgress;
import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardProgressEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FlashcardProgress2FlashcardProgressEntityMapper {

    public FlashcardProgressEntity toFlashcardProgressEntity(FlashcardProgress flashcardProgress) {
        if (flashcardProgress == null) return null;
        FlashcardProgressEntity entity = new FlashcardProgressEntity();
        entity.setProgressDegree(flashcardProgress.getProgressDegree());
        return entity;
    }

    public List<FlashcardProgressEntity> toFlashcardProgressEntityList(List<FlashcardProgress> flashcardProgress) {
        List<FlashcardProgressEntity> entities = new ArrayList<>(flashcardProgress.size());
        for (FlashcardProgress entity : flashcardProgress) {
            entities.add(toFlashcardProgressEntity(entity));
        }
        return entities;
    }
}
