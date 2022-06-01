package cz.cvut.fel.ear.lingo.flashcard.adapter;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;

import java.util.List;

public interface FlashcardRepositoryAdapter {

    void createFlashcard(Flashcard flashcard);

    Flashcard getFlashcardById(Long id);

    List<Flashcard> findAllFlashcards();

    void updateFlashcard(Flashcard flashcard);

    void deleteFlashcard(Long id);

    void restoreFlashcard(Long id);

    List<Flashcard> getFlashcardByWord(String word);
}
