package cz.cvut.fel.ear.lingo.statistics.domain;

import cz.cvut.fel.ear.lingo.flashcard.domain.FlashcardProgress;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Statistic {

    private Long id;
    private List<String> achievements;
    private List<FlashcardProgress> flashcardProgresses;

}