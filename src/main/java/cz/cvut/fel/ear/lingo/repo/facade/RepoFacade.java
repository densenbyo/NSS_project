package cz.cvut.fel.ear.lingo.repo.facade;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import cz.cvut.fel.ear.lingo.repo.domain.Repo;
import cz.cvut.fel.ear.lingo.repo.use_case.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepoFacade {

    private final GetRepoByIdUseCase getRepoByIdUseCase;
    private final GetAllRepoUseCase getAllRepoUseCase;
    private final AddFlashcardToRepoUseCase addFlashcardToRepoUseCase;

    private final RemoveFlashcardFromRepoUseCase removeFlashcardFromRepoUseCase;
    private final AddFlashcardDeckToRepoUseCase addFlashcardDeckToRepoUseCase;
    private final RemoveFlashcardDeckFromRepoUseCase removeFlashcardDeckFromRepoUseCase;

    public Repo findRepoById(Long id) {
        return getRepoByIdUseCase.execute(id);
    }

    public List<Repo> findAllRepo() {
        return getAllRepoUseCase.execute();
    }

    public void addFlashcardToRepo(Long idRepo, Flashcard flashcard) {
        addFlashcardToRepoUseCase.execute(idRepo, flashcard);
    }

    public void removeFlashcardToRepo(Long idRepo, Flashcard flashcard) {
        removeFlashcardFromRepoUseCase.execute(idRepo, flashcard);
    }

    public void addFlashcardDeckToRepo(Long idRepo, FlashcardDeck flashcardDeck) {
        addFlashcardDeckToRepoUseCase.execute(idRepo, flashcardDeck);
    }

    public void removeFlashcardDeckFromRepo(Long idRepo, FlashcardDeck flashcardDeck) {
        removeFlashcardDeckFromRepoUseCase.execute(idRepo, flashcardDeck);
    }
}
