package cz.cvut.fel.ear.lingo.flashcarddeck.adapter;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;

import java.util.List;

public interface FlashcardDeckRepositoryAdapter {

    void saveFlashcardDeck(FlashcardDeck flashcardDeck);

    List<FlashcardDeck> findAllFlashcards();

    FlashcardDeck findFlashcardDeckById(Long id);

    void updateFlashcardDeck(Long id, FlashcardDeck flashcardDeck);

    void deleteFlashcardDeck(Long id);

    void restoreFlashcardDeck(Long id);

    void addFlashcardToFlashcardDeck(Long id, Flashcard flashcard);

    void removeFlashcardToFlashcardDeck(Long id, Flashcard flashcard);
}
