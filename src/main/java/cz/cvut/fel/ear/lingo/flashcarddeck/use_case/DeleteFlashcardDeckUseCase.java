package cz.cvut.fel.ear.lingo.flashcarddeck.use_case;

import cz.cvut.fel.ear.lingo.flashcarddeck.adapter.FlashcardDeckRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteFlashcardDeckUseCase {

    private final FlashcardDeckRepositoryAdapter adapter;

    public void execute(Long id) {
        adapter.deleteFlashcardDeck(id);
    }
}
