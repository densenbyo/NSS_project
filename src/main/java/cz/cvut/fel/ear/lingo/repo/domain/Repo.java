package cz.cvut.fel.ear.lingo.repo.domain;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Repo {

    private Long id;
    private List<FlashcardDeck> flashcardDecks;
    private List<Flashcard> flashcards;

}
