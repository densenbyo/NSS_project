package cz.cvut.fel.ear.lingo.flashcard.mapper;

import cz.cvut.fel.ear.lingo.flashcard.domain.FlashcardProgress;
import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardProgressModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlashcardProgress2FlashcardProgressModelMapper {

    public FlashcardProgressModel toFlashcardProgressModel(FlashcardProgress flashcardProgress) {
        if (flashcardProgress == null) return null;
        return FlashcardProgressModel.builder()
                .id(flashcardProgress.getId())
                .flashcardId(flashcardProgress.getFlashcardId())
                .progressDegree(flashcardProgress.getProgressDegree())
                .build();
    }

    public List<FlashcardProgressModel> toFlashcardProgressModelList(List<FlashcardProgress> flashcardProgresses) {
        List<FlashcardProgressModel> progressModels = new ArrayList<>(flashcardProgresses.size());
        for (FlashcardProgress entity: flashcardProgresses) {
            progressModels.add(toFlashcardProgressModel(entity));
        }
        return progressModels;
    }
}
