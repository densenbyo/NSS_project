package cz.cvut.fel.ear.lingo.flashcard.use_case;


import cz.cvut.fel.ear.lingo.flashcard.adapter.FlashcardRepositoryAdapter;
import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetFlashcardByIdUseCase {

    private final FlashcardRepositoryAdapter adapter;

    public Flashcard execute(Long id) {
        return adapter.getFlashcardById(id);
    }
}