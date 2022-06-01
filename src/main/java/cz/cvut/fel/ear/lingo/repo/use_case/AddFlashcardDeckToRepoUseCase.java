package cz.cvut.fel.ear.lingo.repo.use_case;

import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import cz.cvut.fel.ear.lingo.repo.adapter.RepoRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddFlashcardDeckToRepoUseCase {

    private final RepoRepositoryAdapter adapter;

    public void execute(Long id, FlashcardDeck flashcardDeck) {
        adapter.addFlashcardDeckToRepo(id, flashcardDeck);
    }
}