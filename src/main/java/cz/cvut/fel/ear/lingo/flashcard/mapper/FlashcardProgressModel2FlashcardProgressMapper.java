package cz.cvut.fel.ear.lingo.flashcard.mapper;

import cz.cvut.fel.ear.lingo.flashcard.domain.FlashcardProgress;
import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardProgressModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FlashcardProgressModel2FlashcardProgressMapper {

    public FlashcardProgress toFlashcardProgress(FlashcardProgressModel flashcardProgressModel) {
        if (flashcardProgressModel == null) return null;
        FlashcardProgress flashcardProgress = new FlashcardProgress();
        flashcardProgress.setId(flashcardProgressModel.getId());
        flashcardProgress.setFlashcardId(flashcardProgressModel.getFlashcardId());
        flashcardProgress.setProgressDegree(flashcardProgressModel.getProgressDegree());
        return flashcardProgress;
    }

    public List<FlashcardProgress> toFlashcardProgressList(List<FlashcardProgressModel> flashcardProgressModels) {
        List<FlashcardProgress> contentModels = new ArrayList<>(flashcardProgressModels.size());
        for (FlashcardProgressModel entity : flashcardProgressModels) {
            contentModels.add(toFlashcardProgress(entity));
        }
        return contentModels;
    }
}
