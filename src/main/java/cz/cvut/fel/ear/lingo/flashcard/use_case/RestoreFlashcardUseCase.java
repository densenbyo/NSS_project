package cz.cvut.fel.ear.lingo.flashcard.use_case;

import cz.cvut.fel.ear.lingo.flashcard.adapter.FlashcardRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestoreFlashcardUseCase {

    private final FlashcardRepositoryAdapter adapter;

    public void execute(Long id) {
        adapter.restoreFlashcard(id);
    }
}
