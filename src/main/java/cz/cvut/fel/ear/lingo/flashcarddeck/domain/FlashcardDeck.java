package cz.cvut.fel.ear.lingo.flashcarddeck.domain;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FlashcardDeck {

    private Long id;
    private String name;
    private String description;
    private Boolean isPublic;
    private Boolean isRemoved;
    private List<Flashcard> flashcards;
    private Long creatorId;

}