package cz.cvut.fel.ear.lingo.flashcarddeck.facade;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import cz.cvut.fel.ear.lingo.flashcarddeck.use_case.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlashcardDeckFacade {

    private final CreateFlashcardDeckUseCase createFlashcardDeckUseCase;
    private final GetAllFlashcardDecksUseCase getAllFlashcardDecksUseCase;
    private final GetFlashcardDeckByIdUseCase getFlashcardDeckByIdUseCase;
    private final UpdateFlashcardDeckUseCase updateFlashcardDeckUseCase;
    private final DeleteFlashcardDeckUseCase deleteFlashcardDeckUseCase;
    private final RestoreFlashcardDeckUseCase restoreFlashcardDeckUseCase;
    private final AddFlashcardToFlashcardDeckUseCase addFlashcardToFlashcardDeckUseCase;
    private final RemoveFlashcardToFlashcardDeckUseCase removeFlashcardToFlashcardDeckUseCase;

    public void createFlashcardDeck(FlashcardDeck flashcardDeck) {
        createFlashcardDeckUseCase.execute(flashcardDeck);
    }

    public List<FlashcardDeck> findAllFlashcardDeck() {
        return getAllFlashcardDecksUseCase.execute();
    }

    public FlashcardDeck findFlashcardDeckById(Long id) {
        return getFlashcardDeckByIdUseCase.execute(id);
    }

    public void updateFlashcardDeck(Long id, FlashcardDeck flashcardDeck) {
        updateFlashcardDeckUseCase.execute(id, flashcardDeck);
    }

    public void deleteFlashcardDeck(Long id) {
        deleteFlashcardDeckUseCase.execute(id);
    }

    public void restoreFlashcardDeck(Long id) {
        restoreFlashcardDeckUseCase.execute(id);
    }

    public void addFlashcardToFlashcardDeck(Long flashcardDeckId, Flashcard flashcard) {
        addFlashcardToFlashcardDeckUseCase.execute(flashcardDeckId, flashcard);
    }

    public void removeFlashcardToFlashcardDeck(Long flashcardDeckId, Flashcard flashcard) {
        removeFlashcardToFlashcardDeckUseCase.execute(flashcardDeckId, flashcard);
    }
}
