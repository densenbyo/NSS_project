package cz.cvut.fel.ear.lingo.repo.adapter;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import cz.cvut.fel.ear.lingo.repo.domain.Repo;

import java.util.List;

public interface RepoRepositoryAdapter {

    Repo getFlashcardById(Long id);

    List<Repo> findAll();

    void addFlashcardToRepo(Long id, Flashcard flashcard);

    void removeFlashcardToRepo(Long id, Flashcard flashcard);

    void addFlashcardDeckToRepo(Long id, FlashcardDeck flashcardDeck);

    void removeFlashcardDeckToRepo(Long id, FlashcardDeck flashcardDeck);
}
