package cz.cvut.fel.ear.lingo.flashcard.use_case;

import cz.cvut.fel.ear.lingo.flashcard.adapter.FlashcardRepositoryAdapter;
import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllFlashcardsUseCase {

    private final FlashcardRepositoryAdapter adapter;

    public List<Flashcard> execute() {
        return adapter.findAllFlashcards();
    }
}
