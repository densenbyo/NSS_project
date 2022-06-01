package cz.cvut.fel.ear.lingo.flashcard.use_case;

import cz.cvut.fel.ear.lingo.flashcard.adapter.FlashcardJpaRepositoryAdapter;
import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFlashcardUseCase {

    private final FlashcardJpaRepositoryAdapter adapter;

    public void execute(Flashcard flashcard) {
        adapter.createFlashcard(flashcard);
    }
}
