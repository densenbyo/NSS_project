package cz.cvut.fel.ear.lingo.flashcard.facade;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.use_case.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlashCardFacade {

    private final CreateFlashcardUseCase createFlashcardUseCase;
    private final GetFlashcardByIdUseCase getFlashcardByIdUseCase;
    private final GetAllFlashcardsUseCase getAllFlashcardsUseCase;
    private final UpdateFlashcardUseCase updateFlashcardUseCase;
    private final DeleteFlashcardUseCase deleteFlashcardUseCase;

    private final RestoreFlashcardUseCase restoreFlashcardUseCase;
    private final GetFlashcardsByWordUseCase getFlashcardsByWordUseCase;

    public void createFlashcard(Flashcard flashcard){
        createFlashcardUseCase.execute(flashcard);
    }

    public Flashcard findFlashcardById(Long id) {
        return getFlashcardByIdUseCase.execute(id);
    }

    public List<Flashcard> findAllFlashcards() {
        return getAllFlashcardsUseCase.execute();
    }

    public void updateFlashcard(Flashcard flashcard) {
        updateFlashcardUseCase.execute(flashcard);
    }

    public void deleteFlashcard(Long id) {
        deleteFlashcardUseCase.execute(id);
    }

    public void restoreFlashcard(Long id) {
        restoreFlashcardUseCase.execute(id);
    }

    public List<Flashcard> findFlashcardByWord(String word) {
        return getFlashcardsByWordUseCase.execute(word);
    }
}
