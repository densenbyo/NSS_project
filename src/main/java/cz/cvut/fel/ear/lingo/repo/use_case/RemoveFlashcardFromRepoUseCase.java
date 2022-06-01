package cz.cvut.fel.ear.lingo.repo.use_case;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.repo.adapter.RepoRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveFlashcardFromRepoUseCase {

    private final RepoRepositoryAdapter adapter;

    public void execute(Long id, Flashcard flashcard) {
        adapter.removeFlashcardToRepo(id, flashcard);
    }
}