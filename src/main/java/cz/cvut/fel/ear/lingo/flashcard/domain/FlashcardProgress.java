package cz.cvut.fel.ear.lingo.flashcard.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class FlashcardProgress {

    private Long id;
    private Double progressDegree;
    private Long flashcardId;

}
