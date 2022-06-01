package cz.cvut.fel.ear.lingo.statistics.model;

import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardProgressModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StatisticModel {

    private Long id;
    private List<String> achievements;
    private List<FlashcardProgressModel> flashcardProgressModels;

}
