package cz.cvut.fel.ear.lingo.flashcard.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlashcardProgressModel {

    private Long id;
    private Double progressDegree;
    private Long flashcardId;

}
